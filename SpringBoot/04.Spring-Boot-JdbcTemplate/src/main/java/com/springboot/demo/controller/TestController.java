package com.springboot.demo.controller;

import com.springboot.demo.bean.Student;
import com.springboot.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 16:27
 * Email: 494657028@qq.com
 */
@RestController
public class TestController {
    @Autowired
    private StudentDao StudentDao;

    @RequestMapping(value = "/query_student", method = RequestMethod.GET)
    public Student queryStudentBySno(String sno) {
        return StudentDao.queryStudentBySno(sno);
    }

    @RequestMapping(value = "/query_all_student")
    public List<Map<String, Object>> queryAllStudent() {
        return StudentDao.queryStudentListMap();
    }

    @RequestMapping(value = "/add_student", method = RequestMethod.GET)
    public int saveStudent(String sno, String name, String sex) {
        Student student = new Student();
        student.setSno(sno);
        student.setName(name);
        student.setSex(sex);
        return StudentDao.add(student);
    }

    @RequestMapping(value = "/delete_student", method = RequestMethod.GET)
    public int deleteStudentBySno(String sno) {
        return StudentDao.deleteBySno(sno);
    }
}
