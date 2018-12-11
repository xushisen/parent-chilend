package com.ssxu.dao.user;

import com.ssxu.entity.user.AsdUserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类描述：userdao
 * 创建人：徐石森
 * 创建时间：2018/12/11  10:24
 *
 * @version 1.0
 */
@Repository
public interface UserDao {

    /**
     * list查询
     *
     * @return 数据
     */
    List<AsdUserAccount> getList(Map<String, Object> params);
}
