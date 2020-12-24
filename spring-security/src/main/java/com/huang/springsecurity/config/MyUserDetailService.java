package com.huang.springsecurity.config;

import com.huang.springsecurity.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService 为 security 提供的一个用户对象，用于封装登陆后的用户
 * 这里直接为了方便直接封装一个用户对象交给 security 处理。
 */
@Configuration
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        MyUser myUser = new MyUser();
        myUser.setUserName(s);
        myUser.setPassword(passwordEncoder.encode("123456"));
        System.out.println(myUser.getPassword());

        //一个用户可以有多个权限，先new一个用户权限集合
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (StringUtils.equalsIgnoreCase("mrbird", s)) {
            // AuthorityUtils.commaSeparatedStringToAuthorityList("admin"); 返回一个拥有admin权限的对象赋给用户
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        } else {
            // AuthorityUtils.commaSeparatedStringToAuthorityList("test"); 返回一个拥有test权限的对象赋给用户
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("test");
        }
        return new User(s, myUser.getPassword(), myUser.isEnabled(),
                myUser.isAccountNonExpired(),myUser.isCredentialsNonExpired(),
                myUser.isAccountNonLocked(), authorities);
    }
}
