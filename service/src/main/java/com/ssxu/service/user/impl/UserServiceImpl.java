package com.ssxu.service.user.impl;

import com.ssxu.dao.user.UserDao;
import com.ssxu.mytag.Page;
import com.ssxu.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 类描述：
 * 创建人：徐石森
 * 创建时间：2018/12/11  10:48
 *
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Map<String, Object> getList(Map<String, Object> params) {
        Map<String, Object> pageMap = Page.getPageMap(params);
        return Page.resultMap(pageMap, userDao.getList(pageMap));
    }
}
