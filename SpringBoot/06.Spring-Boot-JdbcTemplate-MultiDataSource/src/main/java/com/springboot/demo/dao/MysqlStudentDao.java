package com.springboot.demo.dao;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 18:26
 * Email: 494657028@qq.com
 */
public interface MysqlStudentDao {
    List<Map<String, Object>> getAllStudents();
}
