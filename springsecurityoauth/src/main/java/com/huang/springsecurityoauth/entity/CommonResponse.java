package com.huang.springsecurityoauth.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @program: springbootall
 * @description: 通用返回类
 * @author: hsrxxx
 * @create: 2020-12-26 16:18
 **/
public class CommonResponse extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 6833187938459164271L;

    public CommonResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public CommonResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }
}
