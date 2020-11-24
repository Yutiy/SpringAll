package com.springboot.demo.service;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Author: yutiy
 * Date: 2020/11/24 23:44
 * Email: 494657028@qq.com
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ReactiveMongoTemplate template;

    public Flux<User> getUsers() {
        return userDao.findAll();
    }

    public Mono<User> getUser(String id) {
        return userDao.findById(id);
    }

    public Mono<User> createUser(User user) {
        return userDao.save(user);
    }

    public Mono<Void> deleteUser(String id) {
        return userDao.findById(id).flatMap(user -> userDao.delete(user));
    }

    public Mono<User> updateUser(String id, User user) {
        return userDao.findById(id)
                .flatMap(u -> {
                    u.setName(user.getName());
                    u.setAge(user.getAge());
                    u.setDescription(user.getDescription());
                    return userDao.save(u);
                });
    }

    public Flux<User> getUserByAge(Integer from, Integer to) {
        return userDao.findByAgeBetween(from, to);
    }

    public Flux<User> getUserByName(String name) {
        return userDao.findByNameEquals(name);
    }

    public Flux<User> getUserByDescription(String description) {
        return userDao.findByDescriptionIsLike(description);
    }

    // 利用 zip 解决问题
    public Mono<Page<User>> getUserByCondition1(int size, int page, User user) {
        Query query = getQuery(user);
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        Flux<User> userFlux = template.find(query.with(pageable), User.class);
        Mono<Long> countMono = template.count(Query.of(query).limit(-1).skip(-1), User.class);
        return Mono.zip(userFlux.collectList(), countMono).map(tuple2 ->
                PageableExecutionUtils.getPage(
                        tuple2.getT1(),
                        pageable,
                        tuple2::getT2
                ));
    }

    /**
     * 分页查询，只返回分页后的数据，count值需要通过 getUserByConditionCount
     * 方法获取
     */
    public Flux<User> getUserByCondition(int size, int page, User user) {
        Query query = getQuery(user);
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(page, size, sort);

        return template.find(query.with(pageable), User.class);
    }

    /**
     * 返回 count，配合 getUserByCondition使用
     */
    public Mono<Long> getUserByConditionCount(User user) {
        Query query = getQuery(user);
        return template.count(query, User.class);
    }

    private Query getQuery(User user) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (!StringUtils.isEmpty(user.getName())) {
            criteria.and("name").is(user.getName());
        }
        if (!StringUtils.isEmpty(user.getDescription())) {
            criteria.and("description").regex(user.getDescription());
        }
        query.addCriteria(criteria);
        return query;
    }
}
