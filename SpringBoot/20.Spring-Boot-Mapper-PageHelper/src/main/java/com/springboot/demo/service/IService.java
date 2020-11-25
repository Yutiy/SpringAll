package com.springboot.demo.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/23 16:53
 * Email: 494657028@qq.com
 */
@Service
public interface IService<T> {
    List<T> selectAll();

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);
}
