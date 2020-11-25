package com.springboot.demo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Author: yutiy
 * Date: 2020/11/24 11:10
 * Email: 494657028@qq.com
 */
@Component
public class HelloApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        System.out.println("HelloApplicationRunner: hello spring boot");
    }
}
