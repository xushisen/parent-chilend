package com.ssxu.exception;

import com.ssxu.entity.Ajax;
import com.ssxu.util.StaticVariable;

import java.util.List;
import java.util.Map;

/**
 * 类描述: 异步返回的工具类
 * 创建人:ssxu
 * 创建时间:2018-4-25 下午2:36:49
 *
 * @version 1.0
 */
public class AjaxUtil {

    /**
     * 正确的调用 List<Map<String,Object>>
     *
     * @param list List<Map<String,Object>> 正确时返回的提示
     * @return ajax
     */
    public static Ajax<List<Map<String, Object>>> successData(List<Map<String, Object>> list) {
        return new Ajax<>(list);
    }

    /**
     * 正确的调用  Map<String,Object>
     *
     * @param map Map<String,Object>
     * @return ajax
     */
    public static Ajax<Map<String, Object>> successData(Map<String, Object> map) {
        return new Ajax<>(map);
    }

    /**
     * 正确的调用  返回的是msg
     *
     * @param msg msg
     * @return ajax
     */
    public static Ajax<Map<String, Object>> successMsg(String msg) {
        return new Ajax<>(StaticVariable.AJAXSUCCESS, msg);
    }

    /**
     * 错误的调用
     *
     * @param msg 错误是返回的提示
     * @return ajax
     */
    public static Ajax<String> error(String msg) {
        return new Ajax<>(StaticVariable.AJAXERROR, msg);
    }
}
