package com.huang.springsecurityoauth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: springbootall
 * @description:
 * @author: hsrxxx
 * @create: 2020-12-26 11:26
 **/
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 1984446777720197754L;

    private String userName;
    private String password;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked= true;
    private boolean credentialsNonExpired= true;
    private boolean enabled= true;
}
