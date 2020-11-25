package com.springboot.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yutiy
 * Date: 2020/11/22 18:33
 * Email: 494657028@qq.com
 */
@RestController
@RequestMapping("user")
public class UserController {
    @GetMapping("/{id:\\d+}")
    public void get(@PathVariable String id) {
        System.out.println(id);
        // throw new RuntimeException("user not exist");
    }
}
