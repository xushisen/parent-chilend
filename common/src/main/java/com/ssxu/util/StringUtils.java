package com.ssxu.util;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 返回list拼接的字符串
     *
     * @param list 数据
     * @return 返回值
     */
    public static String splitList(List<Map<String, Object>> list) {
        if (!list.isEmpty()) {
            StringBuilder s = new StringBuilder();
            for (Map<String, Object> map : list) {
                s.append(map.get("application_id")).append(",");
            }
            s.deleteCharAt(s.length() - 1);
            return s.toString();
        }
        return "";
    }

    /**
     * 判断是否为空
     *
     * @param obj 值
     * @return true 空 false 不为空
     */
    public static boolean isEmpty(Object obj) {
        return null == obj || obj.toString().trim().equals("") || obj.toString().equals("0");
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 源字符串
     * @return true 不为空,false 为空
     */
    public static boolean isNotEmpty(String str) {
        return !(str == null || str.trim().isEmpty() || "null".equals(str));
    }

    /**
     * 获取UUID
     *
     * @return 唯一ID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 去掉空格
     */
    public static String getTrim(Object obj) {
        Pattern p = Pattern.compile(RegUtil.TRIM);
        Matcher m = p.matcher(obj.toString());
        return m.replaceAll("");
    }

    /**
     * list 转String字符串
     *
     * @param stringList 数据
     * @return 返回字符串 逗号分隔
     */
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * 根据传入的个数  从A开始返回个数的ASCII码
     *
     * @param num 从A开始后的一共几个
     * @return 对应的ASCII
     */
    public static String numAscii(int num) {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < num; i++) {
            char c = (char) (65 + i);
            s.append(String.valueOf(c)).append(",");
        }
        return s.deleteCharAt(s.length() - 1).toString();
    }

    /**
     * 返回传入id的后8位
     *
     * @param id 原id
     * @return 后8位
     */
    public static String getIdAfterEight(String id) {
        if (isEmpty(id))
            return "";
        else {
            return id.substring(id.length() - 8);
        }
    }

    /**
     * 截取stringBuild的最后一个逗号
     *
     * @param str str
     * @return 结果
     */
    public static String StringBuildComma(StringBuilder str) {
        if (StringUtils.isEmpty(str))
            return "";
        else
            return str.deleteCharAt(str.length() - 1).toString();
    }
}
