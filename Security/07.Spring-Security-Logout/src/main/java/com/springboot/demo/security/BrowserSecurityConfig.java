package com.springboot.demo.security;

import com.springboot.demo.handler.MyAuthenticationFailureHandler;
import com.springboot.demo.handler.MyAuthenticationSuccessHandler;
import com.springboot.demo.handler.MyLogOutSuccessHandler;
import com.springboot.demo.session.MySessionExpiredStrategy;
import com.springboot.demo.validate.code.ValidateCodeFilter;
import com.springboot.demo.validate.smsCode.SmsAuthenticationConfig;
import com.springboot.demo.validate.smsCode.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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
    private MyLogOutSuccessHandler logOutSuccessHandler;

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Autowired
    private SmsCodeFilter smsCodeFilter;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private MySessionExpiredStrategy sessionExpiredStrategy;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登录
                    .loginPage("/authentication/require") // 登录跳转 URL
                    .loginProcessingUrl("/login") // 处理表单登录 URL
                    .successHandler(authenticationSuccessHandler) // 处理登录成功
                    .failureHandler(authenticationFailureHandler) // 处理登录失败
                .and()
                    .rememberMe()
                        .tokenRepository(persistentTokenRepository()) // 配置 token 持久化仓库
                        .tokenValiditySeconds(3600)                   // remember 过期时间，单为秒
                        .userDetailsService(userDetailService)        // 处理自动登录逻辑
                .and()
                    .authorizeRequests() // 授权配置
                        .antMatchers("/authentication/require",
                            "/login.html",
                            "/code/image",
                            "/code/sms",
                            "/session/invalid", "/signOut/success").permitAll() // 无需认证的请求路径
                    .anyRequest().authenticated()
                .and()
                    .sessionManagement()                    // 添加 Session管理器
                    .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true)
                    .expiredSessionStrategy(sessionExpiredStrategy)
                .and()
                .and()
                    .logout()
                    .logoutUrl("/signOut")
                    .logoutSuccessUrl("/signOut/success")
                    // .logoutSuccessHandler(logOutSuccessHandler)
                    .deleteCookies("JSESSIONID")
                .and()
                    .apply(smsAuthenticationConfig); // 将短信验证码认证配置加到 Spring Security 中
    }

    // 将项目中静态资源路径开放出来
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }
}
