package com.springboot.demo.service.impl;

import com.springboot.demo.bean.Student;
import com.springboot.demo.mapper.StudentMapper;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: yutiy
 * Date: 2020/11/19 14:38
 * Email: 494657028@qq.com
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int add(Student student) {
        return studentMapper.add(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public int deleteBySno(String sno) {
        return studentMapper.deleteBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return studentMapper.queryStudentBySno(sno);
    }
}
