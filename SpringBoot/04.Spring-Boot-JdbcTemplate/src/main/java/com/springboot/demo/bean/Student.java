package com.springboot.demo.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: yutiy
 * Date: 2020/11/19 16:26
 * Email: 494657028@qq.com
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -339516038496531943L;
    private String sno;
    private String name;
    private String sex;
}
