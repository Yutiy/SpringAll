package com.springboot.demo.service;

import com.springboot.demo.bean.Student;

/**
 * Author: yutiy
 * Date: 2020/11/19 14:37
 * Email: 494657028@qq.com
 */
public interface StudentService {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    Student queryStudentBySno(String sno);
}
