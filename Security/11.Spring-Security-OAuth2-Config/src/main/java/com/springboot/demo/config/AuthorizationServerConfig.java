package com.springboot.demo.config;

import com.springboot.demo.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Author: yutiy
 * Date: 2020/11/29 下午6:13
 * Email: 494657028@qq.com
 *
 * http://localhost:8080/oauth/token?grant_type=authorization_code&code=OtoqOk&client_id=test&redirect_uri=https://www.ytxcloud.com&scope=all
 *
 * headers:
 *      Authorization: Basic test:test1234   // http://tool.chinaz.com/Tools/Base64.aspx
 *
 * http://localhost:8080/oauth/token?grant_type=password&username=yutiy&password=123456&scope=all
 */
@Order(1000)
@Configuration
@EnableAuthorizationServer  // 创建认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    // @Autowired
    // private TokenStore redisTokenStore;

    @Autowired
    private TokenStore jwtTokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter);
        // endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailService);
        // endpoints.authenticationManager(authenticationManager).tokenStore(redisTokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("test1")
                    .secret(new BCryptPasswordEncoder().encode("test1111"))
                    .accessTokenValiditySeconds(3600)
                    .refreshTokenValiditySeconds(864000)
                    .scopes("all", "a", "b", "c")
                    .authorizedGrantTypes("password")
                .and()
                    .withClient("test2")
                    .secret(new BCryptPasswordEncoder().encode("test2222"))
                    .accessTokenValiditySeconds(7200);
    }
}
