package com.ssxu;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

/**
 * 类描述：
 * 创建人：徐石森
 * 创建时间：2018/12/12  10:01
 *
 * @version 1.0
 */
public class MyZuulFilter extends ZuulFilter {

    //可以在请求被路由之前调用
    @Override
    public String filterType() {
        return "pre";
    }

    //filter执行顺序，通过数字指定 ,优先级为0，数字越大，优先级越低
    @Override
    public int filterOrder() {
        return 0;
    }

    //是否执行该过滤器，此处为true，说明需要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println("--->>> TokenFilter {},{}"+request.getMethod()+".....//////"+request.getRequestURL().toString());
        return null;
    }
}
