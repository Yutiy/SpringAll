package com.springboot.demo.service.impl;

import com.springboot.demo.bean.Student;
import com.springboot.demo.service.StudentService;
import org.springframework.stereotype.Repository;

/**
 * Author: yutiy
 * Date: 2020/11/23 17:25
 * Email: 494657028@qq.com
 */
@Repository("studentService")
public class StudentServiceImpl extends BaseService<Student> implements StudentService {
}
