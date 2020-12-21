package com.huang.springsecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = ((SavedRequest) savedRequest).getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) // 根据请求是否以.html为结尾来对应不同的处理方法。
                redirectStrategy.sendRedirect(request, response, "/login.html"); // 如果是以.html结尾，那么重定向到登录页面
        }
        return "访问的资源需要身份认证！"; // 否则返回”访问的资源需要身份认证！”信息,并且HTTP状态码为401（HttpStatus.UNAUTHORIZED）
    }
}
