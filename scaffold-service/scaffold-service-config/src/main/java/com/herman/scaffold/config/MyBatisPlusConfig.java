package com.herman.scaffold.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsh
 * @create 2019-11-13 12:45
 **/
@Configuration
@Slf4j
@MapperScan(basePackages = {"com.herman.scaffold.dao"})
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        log.debug("注册分页插件");
        return new PaginationInterceptor();
    }

//    /**
//     * SQL执行效率插件
//     */
//    @Bean
//    @Profile({"test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        return new PerformanceInterceptor();
//    }


}
