package com.herman.scaffold.feign;

import com.herman.scaffold.feign.hystrix.OrderServiceHystrix;
import com.herman.scaffold.interceptor.OAuthFeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hsh on 19/11/11.
 */
@FeignClient(value = "eureka-client-scaffold-order-service",
        fallback = OrderServiceHystrix.class,
        configuration = OAuthFeignInterceptor.class)
public interface OrderServiceFeign {

    @RequestMapping(value = "/scaffold-order/{userId}", method = RequestMethod.GET)
    Object getOrderByUserId(@PathVariable(value = "userId") Long userId);
}
