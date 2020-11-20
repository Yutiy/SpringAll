package com.springboot.demo.service;

import com.springboot.demo.bean.User;

/**
 * Author: yutiy
 * Date: 2020/11/21 00:31
 * Email: 494657028@qq.com
 */
public interface UserService {
    User findByName(String userName);
    void saveUser(User user);
}
