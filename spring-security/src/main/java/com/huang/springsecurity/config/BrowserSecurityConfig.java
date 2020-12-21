package com.huang.springsecurity.config;

import com.huang.springsecurity.handler.MyAuthenticationFailureHandler;
import com.huang.springsecurity.handler.MyAuthenticationSucessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单方式
//                .loginPage("/login.html") // 指定登陆页面
                .loginPage("/authentication/require")
                .loginProcessingUrl("/login") // 对应登陆表单中的 action=“/login”
                .successHandler(authenticationSucessHandler) // 处理登录成功的方法
                .failureHandler(authenticationFailureHandler) // 处理登陆失败的方法
                .and()
                .authorizeRequests()  // 授权配置
                .antMatchers("/authentication/require","/login.html").permitAll() // 跳转到登陆页面的请求不拦截，否则无限循环
                .anyRequest()  // 所有请求
                .authenticated() // 都需要验证
                .and().csrf().disable(); // 关闭CSRF攻击防御
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
