package com.ssxu.util;

import java.util.List;

/**
 * 类描述：list工具类
 * 创建人：徐石森
 * 创建时间：2018/11/16  14:17
 *
 * @version 1.0
 */
public class ListUtils {

    /**
     * 判断list是否为空
     *
     * @param list list
     * @return true 是  false 不是
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || list.isEmpty();
    }

    /**
     * 判断list是否为空
     *
     * @param list list
     * @return true 不为空  false 为空
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

}
