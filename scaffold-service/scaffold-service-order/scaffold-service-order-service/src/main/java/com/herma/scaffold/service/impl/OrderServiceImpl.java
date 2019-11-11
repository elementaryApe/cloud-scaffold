package com.herma.scaffold.service.impl;


import com.herma.scaffold.service.IOrderService;
import com.herman.scaffold.util.UUIDUtils;
import org.springframework.stereotype.Service;
import com.herman.scaffold.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hsh
 * @create 2019-11-11 18:00
 **/
@Service
public class OrderServiceImpl implements IOrderService {

    @Override
    public List<OrderResponse> getUserOrders(Long userId) {
        if(userId==1L){
            List<OrderResponse> list=new ArrayList<OrderResponse>();
            list.add(new OrderResponse(UUIDUtils.getUUID(),"第一个订单"));
            return list;
        }
        return null;
    }
}
