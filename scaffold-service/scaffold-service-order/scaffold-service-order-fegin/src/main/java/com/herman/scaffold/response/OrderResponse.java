package com.herman.scaffold.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hsh
 * @create 2019-11-11 17:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String orderId;
    private String orderName;

}
