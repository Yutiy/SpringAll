package com.springboot.demo.validate;


import org.springframework.security.core.AuthenticationException;

/**
 * Author: yutiy
 * Date: 2020/11/27 14:08
 * Email: 494657028@qq.com
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5022575393500654458L;

    ValidateCodeException(String message) {
        super(message);
    }
}
