package com.springboot.demo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.demo.bean.Student;
import com.springboot.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void testInsert() throws Exception {
        Student student = new Student();
        student.setSno("004");
        student.setName("scott");
        student.setSex("M");
        studentService.save(student);
    }

    @Test
    public void testSelect() throws Exception {
        Example example = new Example(Student.class);
        example.createCriteria().andCondition("name like '%i%'");
        example.setOrderByClause("sno desc");
        List<Student> studentList = studentService.selectByExample(example);
        for (Student u : studentList) {
            System.out.println(u.getName());
        }

        List<Student> all = studentService.selectAll();
        for (Student u : all) {
            System.out.println(u.getName());
        }

        Student student = new Student();
        student.setSno("004");
        student = studentService.selectByKey(student);
        System.out.println(student.getName());
    }

    @Test
    public void testDelete() throws Exception {
        Student student = new Student();
        student.setSno("004");
        studentService.delete(student);
    }

    @Test
    public void testPage() throws Exception {
        PageHelper.startPage(2, 2);
        List<Student> list = studentService.selectAll();
        PageInfo<Student> pageInfo = new PageInfo<Student>(list);
        List<Student> result = pageInfo.getList();
        for (Student u : result) {
            System.out.println(u.getName());
        }
    }
}
