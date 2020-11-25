package com.springboot.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    // 准备阶段: 配置源 -> 推断应用类型 -> 加载应用上下文初始器 -> 加载应用事件监听器 -> 推断入口类
    // 运行阶段: 开启时间监听 -> 开启运行监听器 -> 创建 Environment -> 是否打印Banner -> 创建Context -> 装配Context -> Refresh Context -> 广播应用已启动 -> 执行Runner -> 广播应用运行中
    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);
        new SpringApplicationBuilder(Application.class)
                .bannerMode(Banner.Mode.CONSOLE)
                .web(WebApplicationType.NONE)
                .profiles("dev")
                .run(args);
    }

}
