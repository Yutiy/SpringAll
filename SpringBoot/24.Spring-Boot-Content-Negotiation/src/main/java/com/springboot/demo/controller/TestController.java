package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Author: yutiy
 * Date: 2020/11/24 14:43
 * Email: 494657028@qq.com
 */
//@RestController
@Controller
public class TestController {
    @GetMapping(value = "test", consumes = "text/properties")
    @ResponseBody
    public Properties test(@RequestBody Properties properties) {
        return properties;
    }

    @GetMapping(value = "test1", consumes = "text/properties")
    public Properties test1(Properties properties) {
        return properties;
    }
}
