package com.ssxu.util;

import com.ssxu.sso.Client;

import java.util.Iterator;
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

    /**
     * SSO校验list里面是否有存在该地址的退出对象 有的话移出  因为每次的sessionId都不一样  移出原先的重新添加新的
     *
     * @param clients 退出地址对象集合
     * @param url     url
     */
    public static void isEmptySSO(List<Client> clients, String url) {
        Iterator<Client> iterator = clients.iterator();
        while (iterator.hasNext()) {
            Client client = iterator.next();
            if (client.getUrl().equals(url)) {
                iterator.remove();
                break;
            }
        }
    }

}
