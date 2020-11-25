package com.springboot.demo.config;

import com.springboot.demo.filter.TimeFilter;
import com.springboot.demo.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/22 18:39
 * Email: 494657028@qq.com
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Autowired
    private TimeInterceptor timeInterceptor;

    @Bean
    public FilterRegistrationBean<TimeFilter> timeFilter() {
        FilterRegistrationBean<TimeFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        filterRegistrationBean.setFilter(timeFilter);

        List<String> urlList = new ArrayList<>();
        urlList.add("/*");

        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
