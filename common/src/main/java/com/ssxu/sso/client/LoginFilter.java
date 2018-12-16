package com.ssxu.sso.client;

import com.ssxu.sso.SessionUser;
import com.ssxu.util.EncrypBase;
import com.ssxu.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录拦截
 * ssxu
 */
public class LoginFilter implements Filter {
	private Logger log = LoggerFactory.getLogger(getClass());

	// 访问的域名  需要替换下面的url  只需要到端口
	@Value("${DOMAIN_NAME}")
	private String DOMAIN_NAME = "http://47.93.23.219:8026";
	@Value("${SSO_SERVER_URL}")
	// sso系统的验证登录地址  http://47.93.23.219:8333/SSOLogin
	private String SSO_SERVER_URL;
	// sso系统的验证token地址  http://47.93.23.219:8333/checkToken
	@Value("${SSO_SERVER_VERIFY_URL}")
	private String SSO_SERVER_VERIFY_URL;
	// 不需要拦截的url  mistakeV/saveMistakeA;
	@Value("${EXCLUDED_URLS}")
	private String EXCLUDED_URLS;

	@Override
	public void init(FilterConfig filterConfig){

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String authority = "";

		// true 不需要拦截  false需要拦截
		boolean isExclude = false;
		// 不需要拦截的url
		String[] excludeUrl = EXCLUDED_URLS.split(";");
		for (String excludedUrl : excludeUrl) {
			if (request.getServletPath().substring(1).contains(excludedUrl)) {
				isExclude = true;
				break;
			}
		}

		// 有不需要拦截的url 直接放走
		if (isExclude) {
			chain.doFilter(req, res);
			return;
		}

		if (StringUtils.isNotEmpty(request.getParameter("exit"))){
			session.invalidate();
			log.info("作业退出成功");
		} else {
			String urls = DOMAIN_NAME + request.getRequestURI();
			if(request.getParameter("token") != null) {
				// 验证token有效性
				session.setAttribute("token", request.getParameter("token"));
				response.sendRedirect(SSO_SERVER_VERIFY_URL + "?token=" + request.getParameter("token") + "&callback=" + urls);
				return;
			} else if (request.getParameter("params") != null){
				// 验证成功  返回数据
				String params = request.getParameter("params").replaceAll(" ", "+");
				// 解密
				SessionUser user = com.alibaba.fastjson.JSON.parseObject(EncrypBase.decodeStr(params),SessionUser.class);
				try {
					log.info("作业开始设置session"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					session.setAttribute("userId", user.getUserId());
					session.setAttribute("tenantId", user.getTenantId());
					session.setAttribute("orgId", user.getOrgId());
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("userOrgDate", user.getUserOrgDate());
					log.info("作业设置session完毕"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"id==="+session.getAttribute("userId"));
					// 为了不让地址栏带有加密的token参数
					response.sendRedirect(urls);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != session.getAttribute("userId") && StringUtils.isNotEmpty(session.getAttribute("userId").toString())) {
				chain.doFilter(req, res);
				return;
			}
			
			// 跳转至sso认证中心
			String callbackURL = DOMAIN_NAME + request.getRequestURI();
			StringBuilder url = new StringBuilder();
			url.append(SSO_SERVER_URL).append("?callbackURL=").append(callbackURL);
			if (StringUtils.isNotEmpty(authority)) {
				url.append("&authority=").append(authority);
			}
			response.sendRedirect(url.toString());
		}
	}

	@Override
	public void destroy() {
	}
}
