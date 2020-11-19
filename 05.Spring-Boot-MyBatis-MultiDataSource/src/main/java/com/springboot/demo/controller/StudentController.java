package com.springboot.demo.controller;

import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 17:46
 * Email: 494657028@qq.com
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("querystudentsfrommysql")
    public List<Map<String, Object>> queryStudentsFromMysql(){
        return this.studentService.getAllStudentsFromMysql();
    }

    @RequestMapping("querystudentsfrommysql1")
    public List<Map<String, Object>> queryStudentsFromMysql1(){
        return studentService.getAllStudentsFromMysql1();
    }
}
