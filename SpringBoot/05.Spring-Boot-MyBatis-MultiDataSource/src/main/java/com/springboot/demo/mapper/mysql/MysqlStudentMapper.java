package com.springboot.demo.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 17:45
 * Email: 494657028@qq.com
 */
@Mapper
public interface MysqlStudentMapper {
    List<Map<String, Object>> getAllStudents();
}
