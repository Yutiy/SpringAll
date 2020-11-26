package com.springboot.shiro.service;

import com.springboot.shiro.domain.Permission;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/26 00:54
 * Email: 494657028@qq.com
 */
public interface UserPermissionService {
    List<Permission> findByUserName(String userName);
}
