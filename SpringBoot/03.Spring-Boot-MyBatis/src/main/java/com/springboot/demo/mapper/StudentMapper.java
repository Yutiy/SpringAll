package com.springboot.demo.mapper;

import com.springboot.demo.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Author: yutiy
 * Date: 2020/11/19 14:33
 * Email: 494657028@qq.com
 */
@Mapper
@Component
public interface StudentMapper {
    int add(Student student);
    int update(Student student);
    int deleteBySno(String sno);
    Student queryStudentBySno(String sno);
}
