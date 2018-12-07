package com.ssxu.fegin;

import com.ssxu.entity.Ajax;
import com.ssxu.exception.AjaxUtil;
import org.springframework.stereotype.Component;

/**
 * 类描述：调用生产者接口的错误机制
 * 创建人：徐石森
 * 创建时间：2018/12/7  9:29
 *
 * @version 1.0
 */
@Component
public class ProducerFailback implements Producer {
    @Override
    public Ajax getData(String params) {
        return AjaxUtil.error("调用生产者数据失败---->>>>参数是"+params);
    }
}
