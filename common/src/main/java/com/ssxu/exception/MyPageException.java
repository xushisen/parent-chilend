package com.ssxu.exception;

/**
 * 类描述:自定义异常重定向错误页面的
 * 创建人:ssxu
 * 创建时间:2018-4-25 下午2:37:20
 *
 * @version 1.0
 */
public class MyPageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MyPageException(String message) {
        super(message);
    }

}
