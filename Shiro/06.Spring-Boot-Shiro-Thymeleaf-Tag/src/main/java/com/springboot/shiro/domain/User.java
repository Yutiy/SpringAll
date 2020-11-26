package com.springboot.shiro.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: yutiy
 * Date: 2020/11/25 11:13
 * Email: 494657028@qq.com
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5440372534300871944L;

    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private String status;
}
