package com.herma.scaffold.service;



import com.herman.scaffold.response.OrderResponse;

import java.util.List;

/**
 * Created by hsh on 19/11/11.
 */
public interface IOrderService {

    List<OrderResponse> getUserOrders(Long userId);
}
