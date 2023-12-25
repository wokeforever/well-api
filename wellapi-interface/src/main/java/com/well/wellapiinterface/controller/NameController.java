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
        return "你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){
        String result = "POST 你的名字是" + user.getUsername();
        return result;
    }


}
