package com.example.wellapigateway.Filter;

import com.example.wellapiclientsdk.Utils.SignUtil;
import com.well.wellapicommon.model.entity.InterfaceInfo;
import com.well.wellapicommon.model.entity.User;
import com.well.wellapicommon.service.InnerInterfaceInfoService;
import com.well.wellapicommon.service.InnerUserInterfaceInfoService;
import com.well.wellapicommon.service.InnerUserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：woke
 * @Date：2023/12/3
 */
@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerInterfaceInfoService interfaceInfoService;

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    private final static String WHITE_LIST = "127.0.0.1";

    private final static String LOCAL_HOST = "http://localhost:8123";

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("custom global filter");
//        请求日志
        ServerHttpRequest request = exchange.getRequest();

        String hostName = request.getLocalAddress().getHostString();
        HttpHeaders headers = request.getHeaders();
        String methodValue = request.getMethodValue();
        String path = LOCAL_HOST+request.getPath().value();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        log.info("请求唯一标识:{}", request.getId());
        log.info("请求方法:{}", methodValue);
        log.info("请求路径:{}", path);
        log.info("请求来源地址:{}", hostName);
        log.info("请求来源地址:{}", request.getRemoteAddress());
        log.info("requestHeaders:{}", headers);
        log.info("requestParams:{}", queryParams);
        log.info("requestBody:{}", request.getBody());
//         （黑白名单）
        ServerHttpResponse response = exchange.getResponse();
        if (!WHITE_LIST.equals(hostName)){
            log.info("请求来源地址:{}", "非法请求");
            return handleNoAuth(response);
        }
//        用户鉴权（判断 ak、sk 是否合法)
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String body = headers.getFirst("body");
        // 实际情况要去数据库查是否分配给该用户
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (invokeUser == null){
            log.info("无权限");
            return handleNoAuth(response);
        }
        //防重放初版  随机数为四位数
        if (Long.parseLong(nonce)>10000){
            return handleNoAuth(response);
        }
        //todo 时间和当前时间不超过5分钟
        Long currentTime = System.currentTimeMillis() / 1000;
        final Long FIVE_MINUTES = 60*5L;
        if(currentTime - Long.parseLong(timestamp) >=  FIVE_MINUTES){
            return handleNoAuth(response);
        }

        //todo 实际是从数据库中取secretKey
        String secretKey = invokeUser.getSecretKey();
        String genSign = SignUtil.genSign(accessKey, secretKey);
        if (!genSign.equals(sign)){
            throw new RuntimeException("无权限");
        }
//        todo 请求的模拟接口是否存在
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = interfaceInfoService.getInterfaceInfo(path, methodValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (interfaceInfo == null){
            throw  new RuntimeException("接口不存在");
        }
        //从数据库中查询接口是否存在，以及请求方法是否匹配
//        请求转发，调用模拟接口
//        Mono<Void> filter = chain.filter(exchange);
//        响应日志
        return handleResponse(exchange,chain,interfaceInfo.getId(),invokeUser.getId());
    }

    public Mono<Void> handleNoAuth(ServerHttpResponse response){
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
    @Override
    public int getOrder() {
        return -1;
    }
    public Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain,Long interfaceId,
                                     Long userId){
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();

            HttpStatus statusCode = originalResponse.getStatusCode();

            if(statusCode == HttpStatus.OK){
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        //log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                //  todo 调用次数+1 invokeCount()
                                try {
                                    innerUserInterfaceInfoService.invokeCount(userId, interfaceId);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                //        调用成功，接口调用次数 + 1
                                sb2.append(data);
                                log.info("响应结果："+data);
                                //log.info("<-- {} {}\n", originalResponse.getStatusCode(),data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            //        调用失败，返回一个规范的错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        }catch (Exception e){
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }

    }
}
