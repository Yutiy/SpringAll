package com.springboot.demo.controller;

import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * Author: yutiy
 * Date: 2020/11/29 下午6:17
 * Email: 494657028@qq.com
 */
@RestController
public class UserController {
    // headers
    // Authorization: token_type access_token
    @GetMapping("index")
    public Object index(Authentication authentication){
        return authentication;
    }

    @GetMapping("index1")
    public Object index(@AuthenticationPrincipal Authentication authentication, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "bearer ");

        return Jwts.parser().setSigningKey("test_key".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
    }
}
