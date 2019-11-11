package com.herman.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hsh
 * @create 2019-11-11 16:28
 **/
@EnableZuulProxy
@SpringCloudApplication
@EnableEurekaClient
public class ScaffoldGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldGatewayApplication.class, args);
    }
}
