package com.springboot.demo;

import com.springboot.demo.bean.ConfigBean;
import com.springboot.demo.bean.TestConfigBean;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class, TestConfigBean.class})
//@ImportResource({"classpath:some-application.xml"})
public class Application {

    // http://www.network-science.de/ascii/
    // java -jar xxx.jar --spring.profiles.active={profile}
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);

        // 不想项目的配置被命令行修改
        // app.setAddCommandLineProperties(false);
        app.run(args);
    }

}
