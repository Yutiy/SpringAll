package com.springboot.demo.mapper.mysql1;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 17:45
 * Email: 494657028@qq.com
 */
@Mapper
public interface Mysql1StudentMapper {
    List<Map<String, Object>> getAllStudents();
}
