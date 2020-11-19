package com.springboot.demo.service.impl;

import com.springboot.demo.mapper.mysql.MysqlStudentMapper;
import com.springboot.demo.mapper.mysql1.Mysql1StudentMapper;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 17:47
 * Email: 494657028@qq.com
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private MysqlStudentMapper mysqlStudentMapper;

    @Resource
    private Mysql1StudentMapper mysql1StudentMapper;

    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql() {
        return mysqlStudentMapper.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql1() {
        return mysql1StudentMapper.getAllStudents();
    }
}
