package com.herman.scaffold.controller;

import com.herman.scaffold.feign.OrderServiceFeign;
import com.herman.scaffold.model.UserInfo;
import com.herman.scaffold.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hsh
 * @create 2019-11-11 17:19
 **/
@RestController
@Slf4j
public class UserController {

    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @Autowired
    private IUserInfoService userInfoService;


    @GetMapping("{id}")
    public Object getUserById(@PathVariable Long id) {
        log.info("user-controller: getUserById ->{}", id);
        UserInfo userInfo = userInfoService.getById(id);
        return UserInfo.assembleUserInfo(userInfo);
    }

    @GetMapping("order/{userId}")
    public Object getUserOrder(@PathVariable Long userId) {
        log.info("user-controller: getUserOrder ->{}", userId);
        return orderServiceFeign.getOrderByUserId(userId);
    }
}
