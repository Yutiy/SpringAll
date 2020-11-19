package com.springboot.demo.bean;

import lombok.Data;

/**
 * Author: yutiy
 * Date: 2020/11/19 23:18
 * Email: 494657028@qq.com
 */
@Data
public class Account {
    private String account;
    private String name;
    private String password;
    private String accountType;
    private String tel;

    public Account(String account, String name, String password, String accountType, String tel) {
        super();
        this.account = account;
        this.name = name;
        this.password = password;
        this.accountType = accountType;
        this.tel = tel;
    }
}
