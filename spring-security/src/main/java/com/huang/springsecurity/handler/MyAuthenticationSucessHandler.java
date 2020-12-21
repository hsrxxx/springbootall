package com.huang.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登陆后的处理方法
 * requestCache：会话请求缓存
 * redirectStrategy：默认重定向策略
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

     @Autowired
     private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(mapper.writeValueAsString(authentication));
        // 冲缓存中获取发起跳转的url
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        // 登陆成功后将路由跳转先前发起跳转的url
//        redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        // 需要指定跳转的页面“/main” 可以将 savedRequest.getRedirectUrl() 修改为 “/main”
        redirectStrategy.sendRedirect(request, response, "/main");
    }
}