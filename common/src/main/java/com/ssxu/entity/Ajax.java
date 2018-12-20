package com.ssxu.entity;

/**
 * 类描述：异步实体类 成功code 是1 失败 code 0 data不管成功失败都有值 失败是提示  成功是数据
 * 创建人：ssxu
 * 创建时间：2018-2-28 上午11:09:47
 *
 * @version 1.0
 */
public class Ajax<T> {
    private Integer code;
    private T data;

    public Ajax(Integer code, T data) {
        this.data = data;
        this.code = code;
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
}
