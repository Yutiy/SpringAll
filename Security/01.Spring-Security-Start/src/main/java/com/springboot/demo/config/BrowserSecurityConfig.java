package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Author: yutiy
 * Date: 2020/11/26 22:57
 * Email: 494657028@qq.com
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                    .withUser("yutiy")
                    .password(passwordEncoder().encode("123456"))
                    .roles("admin")
                    .authorities("biz1", "biz2")
                .and()
                    .withUser("test")
                    .password(passwordEncoder().encode("123456"))
                    .roles("test")
                    .authorities("biz1")
                .and()
                    .passwordEncoder(passwordEncoder()); //配置BCrypt加密
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //禁用跨站csrf攻击防御，后面的章节会专门讲解
                .formLogin()
                    .loginPage("/login.html")       // 一旦用户的请求没有权限就跳转到这个页面
                    .loginProcessingUrl("/login")   // 登陆表单form中action的地址，也就是处理认证请求的路径
                    .usernameParameter("username")  // 登陆表单form中用户名输入框input的name名，不修改的话默认是username
                    .passwordParameter("password")  // 登陆表单form中用户名输入框input的password名，不修改的话默认是password
                    .defaultSuccessUrl("/")         // 登陆认证成功后默认跳转的路径
                .and()
                    .authorizeRequests()
                    .antMatchers("/login.html", "/login").permitAll()   // 不需要通过登陆验证就可以被访问的资源路径
                    .antMatchers("/biz1")      // 资源路径匹配
                        .hasAnyAuthority( "biz1")                       // biz1权限可以访问
                    .antMatchers("/biz2")      // 资源路径匹配
                        .hasAnyAuthority("biz2")                        // biz2权限可以访问
                    .antMatchers("/user")
                        .hasAnyAuthority("ROLE_admin")
                    .antMatchers()       // 资源路径匹配
                        .hasAnyRole("test")                                // admin角色可以访问
                .anyRequest()
                .authenticated();
    }

    // 将项目中静态资源路径开放出来
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }
}
