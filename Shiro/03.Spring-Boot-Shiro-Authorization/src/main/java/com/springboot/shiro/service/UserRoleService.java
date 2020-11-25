package com.springboot.shiro.service;

import com.springboot.shiro.domain.Role;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/26 00:53
 * Email: 494657028@qq.com
 */
public interface UserRoleService {
    List<Role> findByUserName(String userName);
}
