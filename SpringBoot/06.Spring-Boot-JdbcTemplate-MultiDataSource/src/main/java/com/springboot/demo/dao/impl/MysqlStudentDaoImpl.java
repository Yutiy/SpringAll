package com.springboot.demo.dao.impl;

import com.springboot.demo.dao.MysqlStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/19 18:27
 * Email: 494657028@qq.com
 */
@Repository
public class MysqlStudentDaoImpl implements MysqlStudentDao {
    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getAllStudents() {
        return jdbcTemplate.queryForList("select * from student");
    }
}
