package com.herman.scaffold.feign.hystrix;

import com.herman.scaffold.feign.OrderServiceFeign;
import org.springframework.stereotype.Component;
import com.herman.scaffold.response.OrderResponse;

/**
 * @author hsh
 * @create 2019-11-11 18:07
 **/
@Component
public class OrderServiceHystrix implements OrderServiceFeign {

    @Override
    public Object getOrderByUserId(Long userId) {
        return new OrderResponse("-1","发生错误");
    }
}
