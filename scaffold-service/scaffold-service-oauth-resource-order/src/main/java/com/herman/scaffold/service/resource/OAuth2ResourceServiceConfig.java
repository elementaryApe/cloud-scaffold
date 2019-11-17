package com.herman.scaffold.service.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author hsh
 * @create 2019-11-11 23:43
 **/
@Configuration
@EnableResourceServer //开启资源服务器配置
public class OAuth2ResourceServiceConfig extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源服务器本身相关配置
        resources.resourceId("order-server");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //httpSecurity配置 哪些请求需要携带令牌
//        super.configure(http);
        //除了ha 不需要令牌外 其他都需要
//        http.authorizeRequests().antMatchers("/haha").permitAll().anyRequest().authenticated();
        //指定权限
        http.authorizeRequests().antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')");
    }
}
