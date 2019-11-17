package com.herman.scaffold.filter;

import com.herman.scaffold.entity.TokenInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 认证：获取请求令牌，是否有效
 * @author hsh
 * @create 2019-11-16 15:54
 **/
@Slf4j
@Component
public class OAuthFilter extends ZuulFilter {

    private RestTemplate restTemplate = new RestTemplate();


    @Override
    public String filterType() {
        return  FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        log.info("oauth start");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if(StringUtils.startsWith(request.getRequestURI(), "/scaffold/scaffold-auth-server/oauth/token")) {
            return null;
        }

        String authHeader = request.getHeader("Authorization");

        if(StringUtils.isBlank(authHeader)) {
            return null;
        }

        if(!StringUtils.startsWithIgnoreCase(authHeader, "bearer ")) {
            return null;
        }

        try {

            TokenInfo info = getTokenInfo(authHeader);
            request.setAttribute("tokenInfo", info);

        } catch (Exception e) {
            log.error("get token info fail", e);
        }
        return null;
    }

    private TokenInfo getTokenInfo(String authHeader) {

        String token = StringUtils.substringAfter(authHeader, "bearer ");
        //去认证服务器 不走网关本身
        String oauthServiceUrl = "http://127.0.0.1:9090/scaffold-auth-server/oauth/check_token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        setBasicAuth(headers,"gateway", "123456",null);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<TokenInfo> response = restTemplate.exchange(oauthServiceUrl, HttpMethod.POST, entity, TokenInfo.class);

        log.info("token info :" + response.getBody().toString());

        return response.getBody();
    }

    public void setBasicAuth( HttpHeaders headers,String username, String password, @Nullable Charset charset) {
        Assert.notNull(username, "Username must not be null");
        Assert.notNull(password, "Password must not be null");
        if(charset == null) {
            charset = StandardCharsets.ISO_8859_1;
        }

        CharsetEncoder encoder = charset.newEncoder();
        if(encoder.canEncode(username) && encoder.canEncode(password)) {
            String credentialsString = username + ":" + password;
            byte[] encodedBytes = Base64.getEncoder().encode(credentialsString.getBytes(charset));
            String encodedCredentials = new String(encodedBytes, charset);
            headers.set("Authorization", "Basic " + encodedCredentials);
        } else {
            throw new IllegalArgumentException("Username or password contains characters that cannot be encoded to " + charset.displayName());
        }
    }
}
