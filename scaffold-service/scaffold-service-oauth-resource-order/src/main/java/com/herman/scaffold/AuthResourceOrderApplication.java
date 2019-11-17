package com.herman.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hsh
 * @create 2019-11-11 23:40
 **/
@SpringBootApplication
@EnableEurekaClient
public class AuthResourceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthResourceOrderApplication.class,args);
    }
}
