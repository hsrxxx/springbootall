package com.huang.springsecurity.erorr;

import org.springframework.security.core.AuthenticationException;

/**
 * @program: springbootall
 * @description: 验证码类型异常
 * @author: hsrxxx
 * @create: 2020-12-22 10:43
 **/
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5022575393500654458L;

    public ValidateCodeException(String message) {
        super(message);
    }

}
