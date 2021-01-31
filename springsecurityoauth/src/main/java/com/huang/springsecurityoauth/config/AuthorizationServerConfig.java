package com.huang.springsecurityoauth.config;

import com.huang.springsecurityoauth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @program: springbootall
 * @description: 认证服务器
 * @author: hsrxxx
 * @create: 2020-12-26 16:07
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).
                userDetailsService(userDetailService);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 获取令牌时指定 client_id 必须为 ”test“
                .withClient("test")
                // 获取令牌时指定 client-secret 必须为 ”test123“
                .secret(passwordEncoder.encode("test123"))
                // 该client_id支持 password、authorization_code 模式获取令牌，并且可以通过refresh_token来获取新的令牌
                .authorizedGrantTypes("password", "refresh_token", "authorization_code")
                .scopes("all")
                .redirectUris("http://localhost:8080/getCode");
    }
}
