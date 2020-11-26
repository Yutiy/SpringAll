package com.springboot.shiro.service.Impl;

import com.springboot.shiro.domain.Role;
import com.springboot.shiro.mapper.UserRoleMapper;
import com.springboot.shiro.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/26 00:54
 * Email: 494657028@qq.com
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserName(String userName) {
        return userRoleMapper.findByUserName(userName);
    }
}
