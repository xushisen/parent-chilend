package com.ssxu.exception;

import com.ssxu.entity.Ajax;
import com.ssxu.util.StaticVariable;

/**
 * 类描述: 异步返回的工具类
 * 创建人:ssxu
 * 创建时间:2018-4-25 下午2:36:49
 *
 * @version 1.0
 */
public class AjaxUtil {

    /**
     * 正确的调用 有提示 没有数据  主要用于保存提示成功之类用的
     *
     * @param msg 正确时返回的提示
     * @return ajax
     */
    public static Ajax success(String msg) {
        Ajax result = new Ajax(StaticVariable.AJAXSUCCESS, msg);
        return result;
    }

    /**
     * 正确的调用
     *
     * @param object 正确时返回的数据
     * @return ajax
     */
    public static Ajax success(Object object) {
        Ajax result = new Ajax(StaticVariable.AJAXSUCCESS, object);
        return result;
    }

    /**
     * 错误的调用
     *
     * @param msg 错误是返回的提示
     * @return ajax
     */
    public static Ajax error(String msg) {
        Ajax result = new Ajax(StaticVariable.AJAXERROR, msg);
        return result;
    }
}
