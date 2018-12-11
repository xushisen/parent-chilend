package com.ssxu.mytag;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 类描述：freemarker 自定义标签的引入
 * 创建人：ssxu
 * 创建时间：2018/5/23  14:54
 *
 * @version 1.0
 */
@Component
public class FreemarkerConfig {
    @Autowired
    private Configuration configuration;
    @Autowired
    private Page page;
    @PostConstruct
    public void setSharedVariable(){
        configuration.setSharedVariable("page", page);
    }
}
