package com.example.wellapiclientsdk.Client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.wellapiclientsdk.Utils.SignUtil;
import com.example.wellapiclientsdk.model.User;
import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：woke
 * @Date：2023/11/24
 */

public class WellApiClient {

    private String accessKey;

    private String secretKey;

    private final static String LOCAL_PATH = "http://localhost:8090";

    public WellApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    private Map<String,String> getHeaderMap(String body){
        HashMap<String,String> hashMap = new HashMap<>();
        body = new String(body.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        hashMap.put("accessKey",accessKey);
//        hashMap.put("secretKey",secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body",body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis()/1000));
        hashMap.put("sign", SignUtil.genSign(body,secretKey));
        return hashMap;
    }


    public String getNameByGet(String name){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        HttpResponse response = HttpRequest.get(LOCAL_PATH+"/api/name/get")
                .form(paramMap)
                .addHeaders(getHeaderMap(name))
                .execute();
        System.out.println(response.getStatus());
        String result = response.body();
        System.out.println(result);
        return result;
    }


    public String getNameByPost(String name){
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("name",name);
        HttpResponse response = HttpRequest.post(LOCAL_PATH+"/api/name/post")
                .form(formMap)
                .addHeaders(getHeaderMap(name))
                .execute();
        System.out.println(response.getStatus());
        String result = response.body();
        System.out.println(result);

        return result;
    }



    public String getUsernameByPost(String requestParams){
        Gson gson = new Gson();
        User user = gson.fromJson(requestParams, User.class);
        String Json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(LOCAL_PATH+"/api/name/user")
                .addHeaders(getHeaderMap(Json))
                .body(Json)
                .execute();
        System.out.println(response.getStatus());
        String result = response.body();
        System.out.println(result);
        return result;

    }
}
