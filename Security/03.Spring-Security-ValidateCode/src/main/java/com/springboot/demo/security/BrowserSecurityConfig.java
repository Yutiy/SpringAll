package com.springboot.demo.security;

import com.springboot.demo.handler.MyAuthenticationFailureHandler;
import com.springboot.demo.handler.MyAuthenticationSuccessHandler;
import com.springboot.demo.validate.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Author: yutiy
 * Date: 2020/11/27 14:02
 * Email: 494657028@qq.com
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .formLogin() // 表单登录
                    .loginPage("/authentication/require") // 登录跳转 URL
                    .loginProcessingUrl("/login") // 处理表单登录 URL
                    .successHandler(authenticationSuccessHandler) // 处理登录成功
                    .failureHandler(authenticationFailureHandler) // 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
                    .antMatchers("/authentication/require",
                        "/login.html",
                        "/code/image").permitAll() // 无需认证的请求路径
                .anyRequest()  // 所有请求
                .authenticated(); // 都需要认证
    }

    // 将项目中静态资源路径开放出来
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }
}
