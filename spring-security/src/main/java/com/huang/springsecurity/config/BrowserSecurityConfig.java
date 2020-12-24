package com.huang.springsecurity.config;

import com.huang.springsecurity.filter.SmsCodeFilter;
import com.huang.springsecurity.filter.ValidateCodeFilter;
import com.huang.springsecurity.handler.MyAuthenticationFailureHandler;
import com.huang.springsecurity.handler.MyAuthenticationSucessHandler;
import com.huang.springsecurity.handler.MyLogOutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSucessHandler authenticationSucessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private MyLogOutSuccessHandler myLogOutSuccessHandler;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 通过 addFilterBefore() 方法将自定义过滤器 validateCodeFilter 添加到 UsernamePasswordAuthenticationFilter 过滤器前面
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加短信验证码校验过滤器
                .formLogin() // 表单方式
                .loginPage("/login.html") // 指定登陆页面
//                .loginPage("/authentication/require")
                .loginProcessingUrl("/login") // 对应登陆表单中的 action=“/login”
                .successHandler(authenticationSucessHandler) // 处理登录成功的方法
                .failureHandler(authenticationFailureHandler) // 处理登陆失败的方法
            .and()
                // 退出登陆
                .logout()
                .logoutUrl("/signout")
                // 成功后返回到指定url
//                .logoutSuccessUrl("/signout/success")
                // 成功后执行指定逻辑
                .logoutSuccessHandler(myLogOutSuccessHandler)
                // 退出登陆后删除Cookies中的JSESSIONID
                .deleteCookies("JSESSIONID")
                .and()
                //
                .rememberMe()
                .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                .tokenValiditySeconds(3600) // remember 过期时间，单为秒
                //这里的用户对象为自定义封装
                .userDetailsService(myUserDetailService) // 处理自动登录逻辑
            .and()
                .authorizeRequests()  // 授权配置
                .antMatchers(
                        "/authentication/require",
                        "/login.html",
                        "/code/image",
                        "/code/sms",
                        "/signout/success",
                        "/session/invalid").permitAll() // 跳转到登陆页面的请求不拦截，否则无限循环
                .anyRequest()  // 所有请求
                .authenticated() // 都需要验证
                .and()
                .sessionManagement() // 添加 Session管理器
                .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
                .and().csrf().disable() // 关闭CSRF攻击防御
                .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // rememberMe 将每次访问的token记录数据库
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
