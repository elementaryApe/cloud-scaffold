package com.herman.scaffold.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsh
 * @create 2019-11-11 17:23
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {

    private String userName;
    private Long userId;



}
