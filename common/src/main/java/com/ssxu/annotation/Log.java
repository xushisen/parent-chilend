package com.ssxu.annotation;

import java.lang.annotation.*;

/**
 * 类描述：自定义log注解
 * 创建人：徐石森
 * 创建时间：2018/7/16  14:21
 *
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块名称
     * 管理后台 admin 企业后台 system  公众用户 空
     */
    String modelName();
}
