package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // http://localhost:8080/oauth/authorize?response_type=code&client_id=test&redirect_uri=https://www.ytxcloud.com&scope=all&state=hello
    // response_type必须为code，表示授权码模式，client_id就是刚刚在配置文件中手动指定的test，redirect_uri这里随便指定一个地址即可，主要是用来重定向获取授权码的，scope指定为all，表示所有权限
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
