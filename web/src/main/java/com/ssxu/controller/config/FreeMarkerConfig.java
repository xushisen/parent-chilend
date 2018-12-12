package com.ssxu.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 类描述：freekmaker全局配置类
 * 创建人：徐石森
 * 创建时间：2018/12/12  10:35
 *
 * @version 1.0
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Value("${htmlpath}")
    private String path;


    // Spring 初始化的时候加载配置
    @PostConstruct
    public void setConfigure() throws Exception {
        // 加载html的资源路径
        configuration.setSharedVariable("path", path);
    }
}
