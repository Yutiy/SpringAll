package com.springboot.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: yutiy
 * Date: 2020/11/19 12:16
 * Email: 494657028@qq.com
 */
@ConfigurationProperties(prefix = "yutiy.blog")
public class ConfigBean {
    private String name;
    private String title;
    private String wholeTitle;

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

    public String getWholeTitle() {
        return wholeTitle;
    }

    public void setWholeTitle(String wholeTitle) {
        this.wholeTitle = wholeTitle;
    }
}
