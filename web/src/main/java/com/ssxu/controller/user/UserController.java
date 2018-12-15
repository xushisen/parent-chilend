package com.ssxu.controller.user;

import com.ssxu.controller.BaseController;
import com.ssxu.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 类描述：usercontroller
 * 创建人：徐石森
 * 创建时间：2018/12/11  10:51
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
@RefreshScope
public class UserController extends BaseController {

    @Value("${name}")
    private String name;

    @Resource
    private UserService userService;

    /**
     * list查询
     *
     * @param map 返回页面的参数
     * @return 页面地址
     */
    @RequestMapping("list")
    public String list(HttpServletRequest request, Map<String, Object> map) {
        logger.info("进入了userList");
        map.put("list", userService.getList(getRequestParams(request)));
        return "user/list";
    }

    @RequestMapping("/getName")
    @ResponseBody
    public String getName(){
        return name;
    }
}
