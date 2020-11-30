package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Author: yutiy
 * Date: 2020/11/29 下午6:13
 * Email: 494657028@qq.com
 *
 * http://localhost:8080/oauth/token?grant_type=authorization_code&code=OtoqOk&client_id=test&redirect_uri=https://www.ytxcloud.com&scope=all
 *
 * headers:
 *      Authorization: Basic test:test1234   // http://tool.chinaz.com/Tools/Base64.aspx
 *
 * http://localhost:8080/oauth/token?grant_type=password&username=yutiy&password=123456&scope=all
 */
@Order(1000)
@Configuration
@EnableAuthorizationServer  // 创建认证服务器
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
