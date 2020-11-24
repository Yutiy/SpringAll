package com.springboot.demo.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Author: yutiy
 * Date: 2020/11/24 11:10
 * Email: 494657028@qq.com
 */
@Component
public class HelloCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("HelloCommandLineRunner: hello spring boot");
    }
}
