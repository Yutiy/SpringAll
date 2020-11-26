package com.springboot.shiro.service;

import com.springboot.shiro.domain.UserOnline;

import java.util.List;

/**
 * Author: yutiy
 * Date: 2020/11/26 16:28
 * Email: 494657028@qq.com
 */
public interface SessionService {
    List<UserOnline> list();
    boolean forceLogout(String sessionId);
}
