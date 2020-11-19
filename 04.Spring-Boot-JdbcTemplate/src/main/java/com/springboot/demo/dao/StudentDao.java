package com.springboot.demo.dao;

import com.springboot.demo.bean.Student;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 16:29
 * Email: 494657028@qq.com
 */
public interface StudentDao {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    List<Map<String, Object>> queryStudentListMap();
    Student queryStudentBySno(String sno);
}
