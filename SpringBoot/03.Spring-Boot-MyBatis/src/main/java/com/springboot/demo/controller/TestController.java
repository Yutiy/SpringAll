package com.springboot.demo.controller;

import com.springboot.demo.bean.Student;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: yutiy
 * Date: 2020/11/19 14:42
 * Email: 494657028@qq.com
 */
@RestController
public class TestController {
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/queryStudent")
    public Student queryStudentBySno(String sno) {
        return studentService.queryStudentBySno(sno);
    }
}
