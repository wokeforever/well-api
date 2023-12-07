package com.example.wellapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@EnableDubbo
public class WellapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WellapiGatewayApplication.class, args);
    }
//
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("api", r -> r.path("/yupiicu")
//                        .uri("https://yupi.icu"))
//                .route("host_route", r -> r.path("/get")
//                        .uri("http://httpbin.org"))
//                .build();
//    }
}
