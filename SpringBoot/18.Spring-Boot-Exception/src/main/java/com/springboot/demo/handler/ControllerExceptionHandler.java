package com.springboot.demo.handler;

import com.springboot.demo.execption.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: yutiy
 * Date: 2020/11/22 18:26
 * Email: 494657028@qq.com
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)              // 指定了要处理的异常类型
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)           // 指定异常处理方法返回的HTTP状态码为500
    public Map<String, Object> handleUserNotExistsException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", e.getId());
        map.put("message", e.getMessage());
        return map;
    }
}
