package com.herman.scaffold.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份认证
 * @author hsh
 * @create 2019-11-18 14:21
 **/
@Component
public class GatewayAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        if(authException instanceof AccessTokenRequiredException) {
            System.out.println("2. udpate log to 401");
        }else {
            System.out.println("2. add log 401");
        }

        request.setAttribute("logUpdated", "yes");

        super.commence(request, response, authException);
    }
}
