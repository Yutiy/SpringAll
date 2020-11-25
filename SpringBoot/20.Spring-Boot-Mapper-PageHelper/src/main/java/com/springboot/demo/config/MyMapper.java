package com.springboot.demo.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Author: yutiy
 * Date: 2020/11/23 00:25
 * Email: 494657028@qq.com
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
