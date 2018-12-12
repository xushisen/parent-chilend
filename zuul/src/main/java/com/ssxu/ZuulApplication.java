package com.ssxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * 类描述：zuul
 * 创建人：徐石森
 * 创建时间：2018/12/12  9:24
 *
 * @version 1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

    @Bean
    public MyZuulFilter myZuulFilter() {
        return new MyZuulFilter();
    }
}
