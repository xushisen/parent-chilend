package com.ssxu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：   正则验证
 * 创建人：ssxu
 * 创建时间：2017-6-14 上午11:06:15
 *
 * @version 1.0
 */
public class RegUtil {

    /**
     * 手机号
     */
    private static final String PHONE = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$";

    /**
     * 身份证
     */
    private static final String IDCARE = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)";

    /**
     * 邮箱
     */
    private static final String EMAIL = "^([a-z0-9A-Z]+[-|.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 是否是字母
     */
    private static final String ISENGLISH = "[a-zA-Z]+";

    /**
     * 空格
     */
    public static final String TRIM = "\\s*|\t|\r|\n";

    /**
     * 只能是英文字母和数字
     */
    private static final String ENGLISHNUM = "^[0-9a-zA-Z]+$";

    /**
     * 后缀名
     */
    private static final Pattern pattern = Pattern.compile("\\S*[?]\\S*");

    /**
     * 校验手机号
     *
     * @param phone 手机号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPhone(String phone) {
        return Pattern.matches(PHONE, phone);
    }

    /**
     * 校验身份证
     *
     * @param phone 身份证号码
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIdCard(String phone) {
        return Pattern.matches(IDCARE, phone);
    }

    /**
     * 校验邮箱
     *
     * @param email 邮箱
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(EMAIL, email);
    }

    /**
     * 是否是字母
     *
     * @param str 字符串
     * @return 是true，不是false
     */
    public static boolean isEnglish(String str) {
        return Pattern.matches(ISENGLISH, str);
    }

    /**
     * 只能是英文字母和数字
     *
     * @param str 字符串
     * @return 是true，不是false
     */
    public static boolean isEnglishNum(String str) {
        return Pattern.matches(ENGLISHNUM, str);
    }

    /**
     * 提取url中的后缀名
     *
     * @param url url地址
     * @return 后缀名
     */
    public static String getHzm(String url) {
        Matcher matcher = pattern.matcher(url);
        String[] spUrl = url.toString().split("/");
        int len = spUrl.length;
        String endUrl = spUrl[len - 1];
        if (matcher.find()) {
            String[] spEndUrl = endUrl.split("\\?");
            return spEndUrl[0].split("\\.")[1];
        }
        return endUrl.split("\\.")[1];
    }
}
