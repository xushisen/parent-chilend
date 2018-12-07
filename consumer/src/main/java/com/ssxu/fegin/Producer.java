package com.ssxu.fegin;

import com.ssxu.entity.Ajax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 类描述：fegin 调用生产者的接口类
 * 创建人：徐石森
 * 创建时间：2018/12/7  9:26
 *
 * @version 1.0 fallback = ProducerFailback.class
 */
@FeignClient(name = "producer",fallback = ProducerFailback.class)
public interface Producer {
    /**
     * 调用生产者数据
     */
    @RequestMapping(value = "/producer/getData")
    Ajax getData(@RequestParam("params") String params);
}
