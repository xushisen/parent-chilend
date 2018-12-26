package com.ssxu.entity;

import com.ssxu.util.StaticVariable;

/**
 * 类描述：异步实体类 成功code 是1 失败 code 0 msg 提示数据  data 页面需要的数据
 * 创建人：ssxu
 * 创建时间：2018-2-28 上午11:09:47
 *
 * @version 1.0
 */
public class Ajax<T> {
    private Integer code;
    private String msg;
    private T data;

    public Ajax(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Ajax(T data) {
        this.data = data;
        this.code = StaticVariable.AJAXSUCCESS;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
