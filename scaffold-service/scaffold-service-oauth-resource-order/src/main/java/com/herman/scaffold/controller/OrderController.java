package com.herman.scaffold.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hsh
 * @create 2019-11-12 0:01
 **/
@RestController("/orders")
@Slf4j
public class OrderController {

    @PostMapping
    public Object create(@AuthenticationPrincipal String username){
        log.info("OrderController:create ->{}",username);

        return username;
    }

}
