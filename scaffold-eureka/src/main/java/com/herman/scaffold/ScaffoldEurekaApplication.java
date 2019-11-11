package com.herman.scaffold;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hsh
 * @create 2019-11-11 15:47
 **/

@SpringBootApplication
@EnableEurekaServer
public class ScaffoldEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldEurekaApplication.class,args);
    }
}
