package com.springboot.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Author: yutiy
 * Date: 2020/11/29 下午7:39
 * Email: 494657028@qq.com
 */
@Order(1001)
@Configuration
@EnableResourceServer
public class ResourceServerConfig  {

}
