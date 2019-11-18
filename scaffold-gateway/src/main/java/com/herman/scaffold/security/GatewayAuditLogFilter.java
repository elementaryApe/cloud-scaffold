package com.herman.scaffold.security;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证之后
 * @author hsh
 * @create 2019-11-18 13:32
 **/
public class GatewayAuditLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("1. add log for "+user);

        filterChain.doFilter(request, response);
        if(StringUtils.isBlank((String) request.getAttribute("logUpdated"))) {
            System.out.println("3. update log to success");
        }
    }
}
