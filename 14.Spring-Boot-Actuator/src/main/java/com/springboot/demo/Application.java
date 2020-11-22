package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
    @RequestMapping("/")
    String index() {
        return "hello spring boot";
    }

//    https://docs.spring.io/spring-boot/docs/2.4.0.RELEASE/reference/htmlsingle/#production-ready-health
//    GET	/configprops	描述配置属性(包含默认值)如何注入Bean
//    GET	/beans	描述应用程序上下文里全部的Bean，以及它们的关系
//    GET	/threaddump	获取线程活动的快照
//    GET	/env	获取全部环境属性
//    GET	/env/{name}	根据名称获取特定的环境属性值
//    GET	/health	报告应用程序的健康指标，这些值由HealthIndicator的实现类提供
//    GET	/info	获取应用程序的定制信息，这些信息由info打头的属性提供
//    GET	/mappings	描述全部的URI路径，以及它们和控制器(包含Actuator端点)的映射关系
//    GET	/metrics	报告各种应用程序度量信息，比如内存用量和HTTP请求计数
//    GET	/metrics/{name}	报告指定名称的应用程序度量值
//    POST	/shutdown	关闭应用程序，要求endpoints.shutdown.enabled设置为true
//    GET	/httptrace	提供基本的HTTP请求跟踪信息(时间戳、HTTP头等)
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
