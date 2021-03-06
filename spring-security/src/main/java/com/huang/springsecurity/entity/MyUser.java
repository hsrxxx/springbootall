package com.huang.springsecurity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyUser implements Serializable {

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
