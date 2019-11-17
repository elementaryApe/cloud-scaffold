package com.herman.scaffold.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 审计：记录谁什么时候做什么
 * @author hsh
 * @create 2019-11-16 16:12
 **/
@Slf4j
@Component
public class AuditLogFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return  FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("audit log insert");
        // TODO 入库
        return null;
    }
}
