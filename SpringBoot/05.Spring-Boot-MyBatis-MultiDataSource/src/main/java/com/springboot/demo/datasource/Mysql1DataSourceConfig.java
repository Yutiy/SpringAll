package com.springboot.demo.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Author: yutiy
 * Date: 2020/11/19 17:35
 * Email: 494657028@qq.com
 */
@Configuration
@MapperScan(basePackages = Mysql1DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysql1SqlSessionFactory")
public class Mysql1DataSourceConfig {
    // mysql.dao扫描路径
    static final String PACKAGE = "com.springboot.demo.mapper.mysql1";

    // mybatis mapper扫描路径
    static final String MAPPER_LOCATION = "classpath:mapper/mysql1/*.xml";

    @Bean(name = "mysql1Datasource")
    @ConfigurationProperties("spring.datasource.druid.mysql1")
    public DataSource mysqlDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysql1TransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(mysqlDataSource());
    }

    @Bean(name = "mysql1SqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory(@Qualifier("mysql1Datasource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置。
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(Mysql1DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
