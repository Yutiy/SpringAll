package com.springboot.demo.controller;

import com.springboot.demo.bean.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Author: yutiy
 * Date: 2020/11/24 11:19
 * Email: 494657028@qq.com
 */
@RestController
@Validated
public class TestController {
    @GetMapping("test1")
    public String test1(
            @NotBlank(message = "{required}") String name,
            @Email(message = "{invalid}") String email) {
        return "success";
    }

    @GetMapping("test2")
    public String test2(@Valid User user) {
        return "success";
    }
}
