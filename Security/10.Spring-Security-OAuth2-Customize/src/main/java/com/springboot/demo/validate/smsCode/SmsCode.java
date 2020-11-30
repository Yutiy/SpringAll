package com.springboot.demo.validate.smsCode;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Author: yutiy
 * Date: 2020/11/27 15:21
 * Email: 494657028@qq.com
 */
@Data
public class SmsCode {
    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code) {
        this.code = code;
    }

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public SmsCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
