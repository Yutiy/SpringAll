package com.springboot.demo.service;

import com.springboot.demo.bean.Student;

/**
 * Author: yutiy
 * Date: 2020/11/19 23:41
 * Email: 494657028@qq.com
 */
public interface StudentService {
    Student update(Student student);

    void deleteStudentBySno(String sno);

    Student queryStudentBySno(String sno);
}
