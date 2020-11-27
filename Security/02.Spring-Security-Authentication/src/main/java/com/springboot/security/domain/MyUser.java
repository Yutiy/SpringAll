package com.springboot.security.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: yutiy
 * Date: 2020/11/27 11:10
 * Email: 494657028@qq.com
 */
@Data
public class MyUser implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private String userName;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}
