package com.springboot.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/12/1 下午4:11
 * Email: 494657028@qq.com
 */
@RestController
public class TestController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/info")
    public String info() {
        List<String> services = client.getServices();
        for (String service : services) {
            log.info("***********service:" + service);
        }

        StringBuilder str = new StringBuilder();
        List<ServiceInstance> serviceInstances = client.getInstances("Server-Provider");
        for (ServiceInstance serviceInstance : serviceInstances) {
            str
                    .append(serviceInstance.getHost())
                    .append("\n")
                    .append(serviceInstance.getInstanceId())
                    .append("\n")
                    .append(serviceInstance.getPort())
                    .append("\n\n");
        }

        return str.toString();
    }
}
