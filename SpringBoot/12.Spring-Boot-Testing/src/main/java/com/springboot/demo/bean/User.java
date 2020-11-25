package com.springboot.demo.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: yutiy
 * Date: 2020/11/20 17:49
 * Email: 494657028@qq.com
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6222176558369919436L;

    private Long id;

    private String username;

    private String passwd;

    private Date createTime;

    private String status;
}
