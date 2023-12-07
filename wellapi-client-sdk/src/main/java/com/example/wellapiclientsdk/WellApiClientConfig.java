package com.example.wellapiclientsdk;

import com.example.wellapiclientsdk.Client.WellApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：woke
 * @Date：2023/11/27
 */

@Configuration
@Data
@ConfigurationProperties("wellapi.client")
@ComponentScan
public class WellApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public WellApiClient wellApiClient(){
        return new WellApiClient(accessKey,secretKey);
    }
}
