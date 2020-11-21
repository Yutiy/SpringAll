package com.springboot.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: yutiy
 * Date: 2020/11/21 23:49
 * Email: 494657028@qq.com
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2731598327208972274L;

    private Long id;
    private String name;
    private Integer age;
}
