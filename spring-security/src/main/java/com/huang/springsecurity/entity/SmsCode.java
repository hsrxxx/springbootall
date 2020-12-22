package com.huang.springsecurity.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: springbootall
 * @description: 短袖验证码对象
 * @author: hsrxxx
 * @create: 2020-12-22 15:53
 **/
@Data
public class SmsCode {
    //验证码
    private String code;
    //过期时间
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    // 判断是否过期
    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
