package com.springboot.demo.service;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 18:31
 * Email: 494657028@qq.com
 */
public interface StudentService {
    List<Map<String, Object>> getAllStudentsFromMysql();
    List<Map<String, Object>> getAllStudentsFromMysql1();
}
