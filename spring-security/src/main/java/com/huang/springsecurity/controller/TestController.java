package com.huang.springsecurity.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(HttpSession session){
        int interval = session.getMaxInactiveInterval();
        long creationTime = session.getCreationTime();
        long lastAccessedTime = session.getLastAccessedTime();
        return '{'+
            "interval"+": "+interval+','+
            "creationTime"+": "+creationTime+','+
            "lastAccessedTime"+": "+lastAccessedTime+
        '}';
    }

    @GetMapping("main")
    public Object main(){
        // 登录成功后，便可以使用SecurityContextHolder.getContext().getAuthentication()获取到Authentication对象信息。
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @GetMapping("/signout/success")
    public String signout() {
        return "退出成功，请重新登录";
    }
}

