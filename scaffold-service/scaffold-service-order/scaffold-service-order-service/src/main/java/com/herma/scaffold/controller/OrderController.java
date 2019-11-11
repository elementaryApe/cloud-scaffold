package com.herma.scaffold.controller;

import com.herma.scaffold.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hsh
 * @create 2019-11-11 17:41
 **/
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("{userId}")
    public Object getOrderByUserId(@PathVariable Long userId) {
        log.info("order-service: getOrderByUserId ->{}", userId);
        if (userId == 1L) {
            return orderService.getUserOrders(userId);
        }
        return null;
    }
}
