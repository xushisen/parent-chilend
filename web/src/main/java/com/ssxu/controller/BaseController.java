package com.ssxu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：   公共controler
 * 创建人：  ssxu
 * 创建时间：2017-6-23 上午9:21:48
 *
 * @version 1.0
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 封装request参数为map
     *
     * @param request request
     * @return requesst封装map
     */
    protected Map<String, Object> getRequestParams(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, String[]> tmp = request.getParameterMap();
        if (tmp != null) {
            for (String key : tmp.keySet()) {
                String[] values = tmp.get(key);
                returnMap.put(key, values.length == 1 ? values[0].trim() : values);
            }
        }
        return returnMap;
    }
}
