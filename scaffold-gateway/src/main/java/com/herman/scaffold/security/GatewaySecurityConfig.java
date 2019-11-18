package com.herman.scaffold.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * @author hsh
 * @create 2019-11-17 21:30
 **/
@Configuration
public class GatewaySecurityConfig  extends ResourceServerConfigurerAdapter {

    @Autowired
    private GatewayWebSecurityExpressionHandler gatewayWebSecurityExpressionHandler;

    @Autowired
    private GatewayAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private GatewayAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new GatewayRateLimitFilter(), SecurityContextPersistenceFilter.class)//先限流在认证
                .addFilterBefore(new GatewayAuditLogFilter(), ExceptionTranslationFilter.class)//认证之后加审计日志
                .authorizeRequests()
                //请求令牌的请求放过去
                .antMatchers("/scaffold/scaffold-auth-server/**").permitAll()
                .anyRequest().access("#permissionService.hasPermission(request, authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .expressionHandler(gatewayWebSecurityExpressionHandler);
    }


}
