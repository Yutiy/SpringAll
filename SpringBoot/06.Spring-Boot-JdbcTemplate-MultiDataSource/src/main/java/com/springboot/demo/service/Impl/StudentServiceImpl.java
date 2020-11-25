package com.springboot.demo.service.Impl;

import com.springboot.demo.dao.Mysql1StudentDao;
import com.springboot.demo.dao.MysqlStudentDao;
import com.springboot.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 18:31
 * Email: 494657028@qq.com
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private MysqlStudentDao mysqlStudentDao;
    @Autowired
    private Mysql1StudentDao mysql1StudentDao;

    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql() {
        return mysqlStudentDao.getAllStudents();
    }

    @Override
    public List<Map<String, Object>> getAllStudentsFromMysql1() {
        return mysql1StudentDao.getAllStudents();
    }
}
