package com.herma.scaffold.controller;

import com.herma.scaffold.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
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
//    @PreAuthorize("#oauth2.hasScope('write')")
    public Object getOrderByUserId(@PathVariable Long userId, @AuthenticationPrincipal String username) {
        log.info("order-service: getOrderByUserId userId->{}", userId);
        log.info("order-service: getOrderByUserId username->{}", username);
        if (userId == 10000) {
            return orderService.getUserOrders(userId);
        }
        return null;
    }
}
