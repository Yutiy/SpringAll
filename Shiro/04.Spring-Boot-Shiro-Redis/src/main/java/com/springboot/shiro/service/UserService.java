package com.springboot.shiro.service;

import com.springboot.shiro.domain.User;

/**
 * Author: yutiy
 * Date: 2020/11/25 11:21
 * Email: 494657028@qq.com
 */
public interface UserService {
    User findByUserName(String userName);
}
