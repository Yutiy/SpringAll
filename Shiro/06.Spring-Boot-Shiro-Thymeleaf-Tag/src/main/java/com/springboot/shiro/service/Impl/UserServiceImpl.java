package com.springboot.shiro.service.Impl;

import com.springboot.shiro.domain.User;
import com.springboot.shiro.mapper.UserMapper;
import com.springboot.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: yutiy
 * Date: 2020/11/25 11:21
 * Email: 494657028@qq.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
