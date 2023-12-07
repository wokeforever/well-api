package com.well.wellapiinterface.controller;

import com.example.wellapiclientsdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author：woke
 * @Date：2023/11/24
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name,HttpServletRequest request){
        return "GET 你的名字是"+name+"  Header(\"name\"):"+request.getHeader("body");
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){

        System.out.println(name);
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){

//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//
//
//
////        String secretKey = request.getHeader("secretKey");
//
//        if (!accessKey.equals("ff48111a5d9bf0771ac8a05c1d5c66f4")) {
//            throw new RuntimeException("无权限");
//        }
//        if (Long.parseLong(nonce)>10000){
//            throw new RuntimeException("无权限");
//        }
//        //todo 时间和当前时间不超过5分钟
//
//
//        //todo 实际是从数据库中取secretKey
//        String genSign = SignUtil.genSign(body, "woke");
//        if (!genSign.equals(sign)){
////            throw new RuntimeException("无权限");
//        }

        String result = "POST 你的名字是" + user.getUsername();
        return result;
    }


}
