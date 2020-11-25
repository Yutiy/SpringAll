package com.springboot.demo.mapper;

import com.springboot.demo.bean.Student;
import org.apache.ibatis.annotations.*;

/**
 * Author: yutiy
 * Date: 2020/11/19 23:43
 * Email: 494657028@qq.com
 */
@Mapper
public interface StudentMapper {
    @Update("update student set name=#{name},sex=#{sex} where sno=#{sno}")
    int update(Student student);

    @Delete("delete from student where sno=#{sno}")
    void deleteStudentBySno(String sno);

    @Select("select * from student where sno=#{sno}")
    @Results(id = "student", value = {
            @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "sex", column = "sex", javaType = String.class)
    })
    Student queryStudentBySno(String sno);
}
