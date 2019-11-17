package com.herman.scaffold.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hsh
 * @create 2019-11-16 15:48
 **/
@Data
public class TokenInfo {
    // 是否激活
    private boolean active;
    //发给的客户端id
    private String client_id;
    //作用域
    private String[] scope;
    //发给的用户名
    private String user_name;
    //可以访问的资源id
    private String[] aud;
    //时间
    private Date exp;
    //权限
    private String[] authorities;
}
