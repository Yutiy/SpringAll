package com.springboot.demo.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Author: yutiy
 * Date: 2020/11/23 23:27
 * Email: 494657028@qq.com
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface FirstLevelService {
    String value() default "";
}
