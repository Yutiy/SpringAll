package com.springboot.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Author: yutiy
 * Date: 2020/11/24 16:26
 * Email: 494657028@qq.com
 */
@Service
public class TestService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Async
    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> asyncMethod() {
        sleep();
        logger.info("异步方法内部线程名称：{}", Thread.currentThread().getName());
        return new AsyncResult<>("hello async");
    }

    public void syncMethod() {
        sleep();
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
