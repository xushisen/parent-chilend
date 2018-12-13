package com.ssxu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 类描述：config  http://localhost:8091/encrypt/status
 *                http://localhsot:8091/encrypt -d mysercet
 *                http://localhost:8091/decrypt -d fdasfa2341sdfa134214…
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
