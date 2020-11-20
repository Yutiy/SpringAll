package com.springboot.demo.controller;

import com.springboot.demo.bean.User;
import com.springboot.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Author: yutiy
 * Date: 2020/11/21 00:30
 * Email: 494657028@qq.com
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("user/{userName}")
    public User getUserByName(@PathVariable(value = "userName") String userName) {
        return userService.findByName(userName);
    }

    @PostMapping("user/save")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
}
