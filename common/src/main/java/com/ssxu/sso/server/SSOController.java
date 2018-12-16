package com.ssxu.sso.server;

import com.ssxu.sso.Client;
import com.ssxu.util.Address;
import com.ssxu.util.ListUtils;
import com.ssxu.util.StaticVariable;
import com.ssxu.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 类描述：单点登录server代码  实现单点登录只需要在sso的项目里面赋值这段代码  到项目  client 引入client的loginFilter
 *        就可以实现单点登录
 * 创建人：徐石森
 * 创建时间：2018/12/16  11:17
 *
 * @version 1.0
 */
public class SSOController {

    /**
     * 单点登录校验
     *
     * @return 成功给token 进行下一步操作  否则跳转到登录页面  添加返回的地址
     */
    @RequestMapping("/SSOLogin")
    public String SSOLogin(HttpSession session, String callbackURL, RedirectAttributes attributes) {
        // 首先判断是否有session
        Object userId = session.getAttribute("userId");
        if (StringUtils.isEmpty(userId)) {
            // 没有登录跳转登录页面
            return "platform/common/login";
        } else {
            // 校验是否有该系统的权限
            /***List<PmdApplication> application = pmdApplicationService.getApplicationByUserIdAndUrl(userId.toString(), Address.getPathExcludeDomain(callbackURL));
            if (ListUtils.isEmpty(application))
                throw new MyPageException("对不起,您没有访问该系统的权限");*/
            // 有权限  给token
            String token = StringUtils.getUUID();
            StaticVariable.SSOTOKENLIST.add(token);
            attributes.addAttribute("token", token);
            // 已经登陆过 给token
            return "redirect:" + callbackURL;
        }
    }

    /**
     * 单点登录校验token的有效性
     *
     * @param session   session
     * @param token     外部系统传的token
     * @param callback  外部系统返回的url
     * @param sessionId 外部系统传的他们系统的sessionId
     * @return token不正确不返回session  正确重定向外部系统的地址返回session
     */
    @RequestMapping("/checkToken")
    public String checkToken(HttpSession session, String token, String callback, String sessionId, RedirectAttributes attributes) {
        if (StaticVariable.SSOTOKENLIST.contains(token)) {
            // 添加退出时的url
            String url = Address.getYuMing(callback, true) + "?exit=exit";
            // 如果退出的list里面有对应的url 移出 然后重新添加一个新的对象 因为每次的sessionId都不一样  所以必须移出 保证list里面对应的每个系统地址对象只有一个
            ListUtils.isEmptySSO(StaticVariable.SSOEXITURLS, url);
            // 添加退出的对象
            Client client = new Client.Builder()
                    .setSessionId(sessionId)
                    .setToken(token)
                    .setUrl(url).build();
            StaticVariable.SSOEXITURLS.add(client);
            // 校验通过
            attributes.addAttribute("params", session.getAttribute("ssoUser").toString());
            // 移出token 如果为了安全 退出也需要校验token的话 这里就不能移出  这里退出暂时没有校验 为了不让内存溢出  先移出了
            StaticVariable.SSOTOKENLIST.remove(token);
            return "redirect:" + callback;
        } else {
            // token不正确跳转到登录页面
            return "platform/common/login";
        }
    }
}
