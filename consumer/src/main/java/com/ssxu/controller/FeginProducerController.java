package com.ssxu.controller;

import com.ssxu.entity.Ajax;
import com.ssxu.exception.MyException;
import com.ssxu.fegin.Producer;
import com.ssxu.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 类描述：调用生产者的测试controller
 * 创建人：徐石森
 * 创建时间：2018/12/7  9:32
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/consumer")
public class FeginProducerController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private Producer producer;

    @RequestMapping("/getProducerData")
    @ResponseBody
    public Ajax getProducerData(String params) {
        logger.info("进入了调用生产者方法");
        if (StringUtils.isEmpty(params))
            throw new MyException("params不能为空");
        return producer.getData(params);
    }
}
