package com.ssxu.util;

import com.ssxu.sso.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:静态变量
 * 创建人:ssxu
 * 创建时间:2018-4-16 下午4:25:24
 *
 * @version 1.0
 */
public class StaticVariable {

    /**
     * 异常正确的状态  用于AJAX的返回值code
     */
    public static final Integer AJAXSUCCESS = 1;

    /**
     * 异常错误的状态  用于AJAX的返回值code
     */
    public static final Integer AJAXERROR = 0;

    /**
     * 单点登录存token  验证完成之后会清除掉
     */
    public static final List<String> SSOTOKENLIST = new ArrayList<>();

    /**
     * 单点退出存退出的对象  验证成功之后会往这个list里面添加一条数据
     */
    public static final List<Client> SSOEXITURLS = new ArrayList<>();
}
