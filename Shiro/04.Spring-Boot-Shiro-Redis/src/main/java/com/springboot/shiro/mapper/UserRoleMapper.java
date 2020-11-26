package com.springboot.shiro.mapper;

import com.springboot.shiro.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/25 17:35
 * Email: 494657028@qq.com
 */
@Mapper
@Component
public interface UserRoleMapper {
    List<Role> findByUserName(String userName);
}
