package com.huang.springsecurityoauth.contorller;

import com.huang.springsecurityoauth.entity.CommonResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: springbootall
 * @description: 资源服务器测试
 * @author: hsrxxx
 * @create: 2020-12-26 15:42
 **/
@RestController
public class UserContorller {

    @GetMapping("/index")
    public Object index(Authentication authentication){
        return authentication;
    }

    @GetMapping("/getCode")
    public CommonResponse getCode(HttpServletRequest request, HttpServletResponse response){
        return new CommonResponse()
                .put("code",request.getParameter("code"))
                .put("state",request.getParameter("state"));
    }
}
