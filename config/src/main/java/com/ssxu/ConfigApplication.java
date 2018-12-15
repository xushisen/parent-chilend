package com.ssxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 类描述：config  http://localhost:8091/encrypt/status
 *                http://localhsot:8091/encrypt -d mysercet
 *                http://localhost:8091/decrypt -d fdasfa2341sdfa134214…
 *                http://localhost:8091/actuator/bus-refresh  这个地址management.endpoints.web.exposure.include"*" 必须要有  不然刷新会报 Request method 'POST' not supported
 *                !!! {cipher} yml前后需要加单引号  propertos不需要
 *                @@@ 获取加密解密的内容的时候还有动态刷新调用接口的时候 暂时还没搞定加上密码去掉拦截 bootstarp.yml里面的账号密码去来  不然postman请求加密解密会被拦截
 * 创建人：徐石森
 * 创建时间：2018/12/13  9:14
 *
 * @version 1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
