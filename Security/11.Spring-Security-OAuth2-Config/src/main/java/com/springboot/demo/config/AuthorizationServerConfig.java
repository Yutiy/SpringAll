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
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

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
 *
 * refresh_token
 *      localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJtcmJpcmQiLCJzY29wZSI6W10sImF0aSI6IjQ1NmNlZDZjLTgzMTYtNDgyNy1iY2EwLWU4ZjVlMzkyYTcyZCIsImV4cCI6MTU2MjQwMTUzMywibWVzc2FnZSI6ImhlbGxvIHdvcmxkIiwiYXV0aG9yaXRpZXMiOlsiYWRtaW4iXSwianRpIjoiNGYyOTAxMTItZDllYi00ZjQ5LTk0ZDctZmE3ODRlNmEyYWI1IiwiY2xpZW50X2lkIjoidGVzdDEifQ.5ldS0Fn4znrnW7vAAuy1RVKjvV3H01b42om3-uyNz50
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

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancers);

        endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter).tokenEnhancer(enhancerChain).userDetailsService(userDetailService);
        // endpoints.authenticationManager(authenticationManager).tokenStore(jwtTokenStore).accessTokenConverter(jwtAccessTokenConverter).userDetailsService(userDetailService);;
        // endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailService);
        // endpoints.authenticationManager(authenticationManager).tokenStore(redisTokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("test1")
                    .secret(new BCryptPasswordEncoder().encode("test1111"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .accessTokenValiditySeconds(3600)
                    .refreshTokenValiditySeconds(864000)
                    .scopes("all", "a", "b", "c")
                .and()
                    .withClient("test2")
                    .secret(new BCryptPasswordEncoder().encode("test2222"))
                    .accessTokenValiditySeconds(7200);
    }
}
