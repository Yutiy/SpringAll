package com.springboot.demo.execption;

import lombok.Data;

/**
 * Author: yutiy
 * Date: 2020/11/22 18:24
 * Email: 494657028@qq.com
 */
@Data
public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = -1574716826948451793L;

    private String id;
    public UserNotExistException(String id){
        super("user not exist");
        this.id = id;
    }
}
