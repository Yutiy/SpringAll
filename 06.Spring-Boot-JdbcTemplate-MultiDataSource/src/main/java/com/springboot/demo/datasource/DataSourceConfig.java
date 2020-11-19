package com.springboot.demo.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Author: yutiy
 * Date: 2020/11/19 18:24
 * Email: 494657028@qq.com
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "mysqldatasource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysql1datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(
            @Qualifier("mysqldatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "mysql1JdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(
            @Qualifier("mysql1datasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
