package com.ssxu.entity;

/**
 * 类描述：异步实体类
 * 创建人：ssxu
 * 创建时间：2018-2-28 上午11:09:47
 *
 * @version 1.0
 */
public class Ajax {
    private Integer code;
    private String msg;
    private Object data;

    public Ajax(){

    }
    /**
     * 成功的构造 没有msg
     */
    public Ajax(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    /**
     * 错误的构造 没有data
     */
    public Ajax(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
