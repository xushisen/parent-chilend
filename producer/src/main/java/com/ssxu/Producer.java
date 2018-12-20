package com.ssxu;

import com.ssxu.entity.Ajax;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类描述：模拟生产者
 * 创建人：徐石森
 * 创建时间：2018/12/6  16:19
 *
 * @version 1.0
 */
@Controller
@RequestMapping("producer")
public class Producer {

    @Value("${server.port}")
    String serverPort;

    @RequestMapping("/getData")
    @ResponseBody
    public Ajax getData(String params){
        StringBuilder sb = new StringBuilder();
        sb.append("我是生产者返回给消费者的数据====>>>");
        sb.append("客户端给的参数==");
        sb.append(params);
        sb.append("端口号=====>>>");
        sb.append(serverPort);
        return AjaxUtil.success(sb.toString());
    }
}
