package com.springboot.demo.controller;

import com.springboot.demo.bean.BlogProperties;
import com.springboot.demo.bean.ConfigBean;
import com.springboot.demo.bean.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yutiy
 * Date: 2020/11/19 12:14
 * Email: 494657028@qq.com
 */
@RestController
public class IndexController {

    @Autowired
    private  BlogProperties blogProperties;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private TestConfigBean testConfigBean;

    @RequestMapping("/")
    String index() {
        return blogProperties.getName() + "——" + blogProperties.getTitle();
    }

    @RequestMapping("/bean")
    String index1() {
        return configBean.getName() + "——" + configBean.getTitle();
    }

    @RequestMapping("/test_bean")
    String index2() {
        return testConfigBean.getName() + "——" + testConfigBean.getAge();
    }

}
