package com.springboot.shiro.service.Impl;

import com.springboot.shiro.domain.Permission;
import com.springboot.shiro.mapper.UserPermissionMapper;
import com.springboot.shiro.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/26 00:56
 * Email: 494657028@qq.com
 */
@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Override
    public List<Permission> findByUserName(String userName) {
        return userPermissionMapper.findByUserName(userName);
    }
}
