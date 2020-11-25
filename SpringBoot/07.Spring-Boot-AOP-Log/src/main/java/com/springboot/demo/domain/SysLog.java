package com.springboot.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: yutiy
 * Date: 2020/11/19 21:11
 * Email: 494657028@qq.com
 */
@Data
public class SysLog implements Serializable {
    private static final long serialVersionUID = -6309732882044872298L;

    private Integer id;
    private String username;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private Date createTime;
}
