package com.springboot.shiro.mapper;

import com.springboot.shiro.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Author: yutiy
 * Date: 2020/11/25 11:14
 * Email: 494657028@qq.com
 */
@Mapper
@Component
public interface UserMapper {
    User findByUserName(String userName);
}
