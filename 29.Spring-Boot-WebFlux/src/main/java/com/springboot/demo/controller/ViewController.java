package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: yutiy
 * Date: 2020/11/24 23:08
 * Email: 494657028@qq.com
 */
@Controller
public class ViewController {
    @GetMapping("flux")
    public String flux() {
        return "flux";
    }
}
