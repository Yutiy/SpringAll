package com.springboot.shiro.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: yutiy
 * Date: 2020/11/25 17:34
 * Email: 494657028@qq.com
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 7160557680614732403L;

    private Integer id;
    private String url;
    private String name;
}
