package com.herman.scaffold.filter;

import com.herman.scaffold.entity.TokenInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权 决定一个请求是否可以被执行
 * @author hsh
 * @create 2019-11-16 16:15
 **/
@Slf4j
@Component
public class AuthorizationFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("authorization start");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //需要认证则取token信息
        if(isNeedAuth(request)) {

            TokenInfo tokenInfo = (TokenInfo)request.getAttribute("tokenInfo");

            if(tokenInfo != null && tokenInfo.isActive()) {
                if(!hasPermission(tokenInfo, request)) {
                    // TODO 入库
                    log.info("audit log update fail 403");
                    handleError(403, requestContext);
                }

                requestContext.addZuulRequestHeader("username", tokenInfo.getUser_name());
            }else {
                if(!StringUtils.startsWith(request.getRequestURI(), "/scaffold/scaffold-auth-server/oauth/token")) {
                    // TODO 入库
                    log.info("audit log update fail 401");
                    handleError(401, requestContext);
                }
            }
        }

        return null;
    }

    private void handleError(int status, RequestContext requestContext) {
        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseStatusCode(status);
        requestContext.setResponseBody("{\"message\":\"auth fail\"}");
        requestContext.setSendZuulResponse(false);
    }

    /**
     *  是否是权限token
     * @param tokenInfo
     * @param request
     * @return
     */
    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
        // TODO 入库
        return true; //RandomUtils.nextInt() % 2 == 0;
    }

    /**
     * 是否需要认证
     * @param request
     * @return
     */
    private boolean isNeedAuth(HttpServletRequest request) {
        // TODO 入库
        return true;
    }
}
