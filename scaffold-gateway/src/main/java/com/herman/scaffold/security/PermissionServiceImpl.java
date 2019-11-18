
package com.herman.scaffold.security;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //TODO 根据具体请求决定权限
        System.out.println("2 authorize");
        System.out.println(request.getRequestURI());
        return RandomUtils.nextInt() % 2 == 0;
    }

}
