package com.ssxu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述： 关于map的操作
 * 创建人：徐石森
 * 创建时间：2018/7/26  15:29
 *
 * @version 1.0
 */
public class MapUtils {

    private static Logger logger = LoggerFactory.getLogger(MapUtils.class);

    /**
     * object转map
     *
     * @param obj 对象
     * @return map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            logger.error("object转map出错");
        }
        return map;
    }

    /**
     * 判断map是否为空
     *
     * @param map map
     * @return true 为空 false 不为空
     */
    public static boolean isEmpty(Map<String, ?> map) {
        return null == map;
    }

    /**
     * 判断map对应的key是否为空
     *
     * @param map map
     * @return true 为空 false 不为空
     */
    public static boolean isEmptyByKey(Map<String, ?> map, String key) {
        return isEmpty(map) || null == map.get(key);
    }
}
