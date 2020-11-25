package com.springboot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: yutiy
 * Date: 2020/11/24 15:55
 * Email: 494657028@qq.com
 */
@Controller
public class TestController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    // value	指定所支持域的集合，*表示所有域都支持，默认值为*。这些值对应HTTP请求头中的Access-Control-Allow-Origin
    // origins	同value
    // allowedHeaders	允许请求头中的header，默认都支持
    // exposedHeaders	响应头中允许访问的header，默认为空
    // methods	支持请求的方法，比如GET，POST，PUT等，默认和Controller中的方法上标注的一致。
    // allowCredentials	是否允许cookie随请求发送，使用时必须指定具体的域
    // maxAge	预请求的结果的有效期，默认30分钟
    // @CrossOrigin(value = "*")
    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
