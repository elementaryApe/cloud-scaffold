package com.herman.scaffold.service.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * 资源服务器请求认证服务器
 * @author hsh
 * @create 2019-11-11 23:52
 **/
@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices remoteTokenServices=new RemoteTokenServices();
        remoteTokenServices.setClientId("orderService");
        remoteTokenServices.setClientSecret("123456");
        remoteTokenServices.setCheckTokenEndpointUrl("http://127.0.0.1:9090/scaffold-auth-server/oauth/check_token");
        return remoteTokenServices;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();
        oAuth2AuthenticationManager.setTokenServices(tokenServices());
        return oAuth2AuthenticationManager;
    }
}
