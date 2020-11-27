package com.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yutiy
 * Date: 2020/11/26 18:20
 * Email: 494657028@qq.com
 */
@RestController
public class TestController {
    @GetMapping("/")
    public String home() {
        return "hello spring security";
    }

    @GetMapping("/biz1")
    public String biz1() {
        return "biz1";
    }

    @GetMapping("/biz2")
    public String biz2() {
        return "biz2";
    }

    @GetMapping("/user")
    public String sys() {
        return "user";
    }
}
