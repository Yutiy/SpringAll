package com.springboot.ssoapplicationtwo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Author: yutiy
 * Date: 2020/12/1 上午11:21
 * Email: 494657028@qq.com
 */
@RestController
public class UserController {

    @GetMapping("user")
    public Principal user(Principal principal) {
        return principal;
    }
}
