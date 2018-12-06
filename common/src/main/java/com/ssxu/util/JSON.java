package com.ssxu.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 类描述：json工具类
 * 创建人：徐石森
 * 创建时间：2018/9/22  15:52
 *
 * @version 1.0
 */
public class JSON {

    /**
     * 加密问号后面的参数
     *
     * @param key 问号后面的key 也就是等号前面的key
     * @param val 问号后面的value 也就是等号后面的value
     * @return 加密后的字符串
     */
    public static String encryUrl(String[] key, String[] val) {
        JSONObject json = new JSONObject();
        for (int i = 0; i < key.length; i++) {
            json.put(key[i], val[i]);
        }
        String params = json.toString();
        return EncrypBase.encodeStr(params).replaceAll("[\\s*\t\n\r]", "");
    }

    /**
     * 获取问号后面的加密参数并解析
     *
     * @param params 要解密字符串
     * @return 解密后的数据
     */
    public static JSONObject getUrlParams(String params) {
        String param = EncrypBase.decodeStr(params);
        return com.alibaba.fastjson.JSON.parseObject(param);
    }
}
