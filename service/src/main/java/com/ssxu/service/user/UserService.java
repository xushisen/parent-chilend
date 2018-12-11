package com.ssxu.service.user;

import java.util.Map;

/**
 * 类描述：userservice
 * 创建人：徐石森
 * 创建时间：2018/12/11  10:24
 *
 * @version 1.0
 */
public interface UserService {

    /**
     * list查询
     *
     * @return 数据
     */
    Map<String, Object> getList(Map<String, Object> params);
}
