package com.springboot.demo.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author: yutiy
 * Date: 2020/11/19 12:13
 * Email: 494657028@qq.com
 */
@Component
public class BlogProperties {
    @Value("${yutiy.blog.name}")
    private String name;

    @Value("${yutiy.blog.title}")
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
