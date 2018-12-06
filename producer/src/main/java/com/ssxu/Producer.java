package com.ssxu;

import com.ssxu.entity.Ajax;
import com.ssxu.exception.AjaxUtil;
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

    @RequestMapping("/getData")
    @ResponseBody
    public Ajax getData(){
        return AjaxUtil.success("哈哈");
    }
}