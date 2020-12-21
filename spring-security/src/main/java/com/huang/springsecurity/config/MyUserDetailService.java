package com.huang.springsecurity.config;

import com.huang.springsecurity.entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser myUser = new MyUser();
        myUser.setUserName(s);
        myUser.setPassword(passwordEncoder.encode("123456"));
        System.out.println(myUser.getPassword());

        return new User(s, myUser.getPassword(), myUser.isEnabled(),
                myUser.isAccountNonExpired(),myUser.isCredentialsNonExpired(),
                myUser.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
