package com.huang.springsecurityoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @program: springbootall
 * @description: 资源服务器配置
 * @author: hsrxxx
 * @create: 2020-12-26 15:48
 **/
@Order(1001)
@Configuration
@EnableResourceServer
public class ResourceServerConfig {
}
