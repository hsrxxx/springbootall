package com.huang.springsecurity.entity;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @program: springbootall
 * @description: 验证码
 * @author: hsrxxx
 * @create: 2020-12-22 10:13
 **/
@Data
public class ImageCode {

    //图片
    private BufferedImage image;

    //验证码
    private String code;

    //过期时间
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int  expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    //判断是否过期
    public boolean isExpire(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
