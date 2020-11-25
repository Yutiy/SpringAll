package com.springboot.demo.service.impl;

import com.springboot.demo.bean.Student;
import com.springboot.demo.mapper.StudentMapper;
import com.springboot.demo.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: yutiy
 * Date: 2020/11/19 23:42
 * Email: 494657028@qq.com
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student update(Student student) {
        this.studentMapper.update(student);
        return this.studentMapper.queryStudentBySno(student.getSno());
    }

    @Override
    public void deleteStudentBySno(String sno) {
        this.studentMapper.deleteStudentBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return this.studentMapper.queryStudentBySno(sno);
    }
}
