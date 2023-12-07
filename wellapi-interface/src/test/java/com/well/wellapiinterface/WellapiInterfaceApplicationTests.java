package com.well.wellapiinterface;

import com.example.wellapiclientsdk.Client.WellApiClient;
import com.example.wellapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WellapiInterfaceApplicationTests {

    @Resource
    private WellApiClient wellApiClient;

    @Test
    void contextLoads() {
        String di1 = wellApiClient.getNameByGet("di1");
        System.out.println(di1);
        String di2 = wellApiClient.getNameByPost("di2");
        System.out.println(di2);
        User user = new User();
        user.setUsername("di3");
        String usernameByPost = wellApiClient.getUsernameByPost(String.valueOf(user));
        System.out.println(usernameByPost);

    }

}
