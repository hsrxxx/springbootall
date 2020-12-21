package com.huang.springsecurity.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("main")
    public Object main(){
        // 登录成功后，便可以使用SecurityContextHolder.getContext().getAuthentication()获取到Authentication对象信息。
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
