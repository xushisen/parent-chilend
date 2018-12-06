package com.ssxu.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class EncrypBase {
    /**
     * 解密
     *
     * @param pwd 解密前的字符串
     * @return 解密后的字符串
     */
    public static String decodeStr(String pwd) {
        try {
            if (null == pwd) {
                return null;
            }
            return new String(Base64.decodeBase64(pwd.getBytes("utf-8")),
                    "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     *
     * @param pwd 加密前的字符串
     * @return 加密后的字符串
     */
    public static String encodeStr(String pwd) {
        try {
            if (null == pwd) {
                return null;
            }
            return new String(Base64.encodeBase64(pwd.getBytes("utf-8")),
                    "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
