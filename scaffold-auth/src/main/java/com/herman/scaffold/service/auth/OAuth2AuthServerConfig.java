package com.herman.scaffold.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * 认证服务器配置
 *
 * @author hsh
 * @create 2019-11-11 22:30
 **/
@Configuration
@EnableAuthorizationServer//开启认证授权服务器
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;//认证

    @Autowired
    private PasswordEncoder passwordEncoder;//所有的密码加密项

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许哪些资源服务器可以校验令牌 验证条件 一定是经过身份认证的
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // TODO 入库
        //允许的客户端访问认证服务器获取令牌
       clients.inMemory()
               .withClient("orderApp")
               .secret(passwordEncoder.encode("123456"))
               .scopes("read","write")
               .accessTokenValiditySeconds(3600)
               .resourceIds("order-server")//可以访问的资源服务器
               .authorizedGrantTypes("password")//授权类型
               .and()
               .withClient("orderService")
               .secret(passwordEncoder.encode("123456"))
               .scopes("read")
               .accessTokenValiditySeconds(3600)
               .resourceIds("order-server")//可以访问的资源服务器
               .authorizedGrantTypes("password")//授权类型
               .and()
               .withClient("gateway")
               .secret(passwordEncoder.encode("123456"))
               .scopes("read","write")
               .accessTokenValiditySeconds(3600)
               .resourceIds("order-server")//可以访问的资源服务器
               .authorizedGrantTypes("password")//授权类型
       ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // TODO 入库
        //允许哪些用户访问认证服务器
        endpoints.authenticationManager(authenticationManager);
    }
}
