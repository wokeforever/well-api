package com.well.wellapiinterface.controller;

import cn.hutool.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：woke
 * @Date：2023/12/25
 */
@RestController
@RequestMapping("/interface")
public class InterfaceController {


    @GetMapping("/name")
    public String getRandomName(){
        return HttpRequest.get("https://api.lolimi.cn/API/naen/api.php").execute().body();
    }

    @GetMapping("/sentences")
    public String getRandomSentences(){
        return HttpRequest.get("https://api.apiopen.top/api/sentences").execute().body();
    }

}
