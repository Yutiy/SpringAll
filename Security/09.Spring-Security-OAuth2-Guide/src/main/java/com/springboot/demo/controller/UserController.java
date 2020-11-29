package com.springboot.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yutiy
 * Date: 2020/11/29 下午6:17
 * Email: 494657028@qq.com
 */
@RestController
public class UserController {
    // headers
    // Authorization: token_type access_token
    @GetMapping("index")
    public Object index(Authentication authentication){
        return authentication;
    }
}
