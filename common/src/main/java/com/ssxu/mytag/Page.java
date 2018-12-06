package com.ssxu.mytag;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 类描述：自定义分页标签
 * 创建人：ssxu
 * 创建时间：2018/5/23  14:44
 *
 * @version 1.0
 */
@Component
public class Page implements TemplateDirectiveModel {
    private int count = 0; //总条数    需要动态改
    private int pagesize = 20;  //每页的数量 默认20条 改这里记得154行的15也要改
    private int page = 0;   //当前页数   需要动态改
    private int zys = 0;  //总页数
    private int pcys = 7;  //页面上要显示的页数的个数   可以改变

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        count = Integer.parseInt(map.get("count").toString());
        page = Integer.parseInt(map.get("page").toString());
        pagesize = Integer.parseInt(map.get("pagesize").toString());
        zys = (count + pagesize - 1) / pagesize;  //总共的页数

        //获取编译器(Configuration.VERSION_2_3_25);
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
        //在环境中设置变量
        environment.setVariable("pages", builder.build().wrap(getYs(count)));
        environment.setVariable("js", builder.build().wrap(javascript()));
        //输出
        templateDirectiveBody.render(environment.getOut());

    }

    /**
     * 输出js
     *
     * @return 对应的js
     */
    private String javascript() {
        StringBuffer js = new StringBuffer();
        js.append("<script type=\"text/javascript\">");
        js.append("  function paging(page){");
        js.append("     document.getElementById(\"pagebegin\").value=page;");
        js.append("     document.forms[0].submit();");
        js.append("  }");
        js.append("  function isPositiveInteger(s){");
        js.append("     var re=/^\\+?[1-9][0-9]*$/;return re.test(s)");
        js.append("  }");
        js.append("  function tiao(page,zys){");
        js.append("     var event = window.event || arguments.callee.caller.arguments[0];");
        js.append("     if(event.keyCode == 13){");
        js.append("         if(isPositiveInteger(page)){");
        js.append("             if(page<=zys){paging(page)}else{$.ts({\"content\":\"输入的页数大于总页数\",\"direction\":\"around\",\"code\":\"0\"});}}else{$.ts({\"content\":\"只能输入非零的正整数\",\"direction\":\"around\",\"code\":\"0\"});}}");
        js.append("  }");
        js.append("  function tiaoclick(zys){");
        js.append("         var page = document.getElementById(\"sen\").value;");
        js.append("         if(isPositiveInteger(page)){");
        js.append("             if(page<=zys){paging(page)}else{$.ts({\"content\":\"输入的页数大于总页数\",\"direction\":\"around\",\"code\":\"0\"});}}else{$.ts({\"content\":\"只能输入非零的正整数\",\"direction\":\"around\",\"code\":\"0\"});}");
        js.append("  }");
        js.append("  function defaultYs(ys){");
        js.append("     document.getElementById(\"pagesize\").value=ys;");
        js.append("     document.forms[0].submit();");
        js.append("  }");
        js.append("</script>");
        return js.toString();
    }

    /**
     * 没有上页下页的那种格式
     * 当前页老是在中间  然后计算2边的页数  除了刚开始和最后
     * 默认一共7个  当前页数在最中间   当前页数超过4然后前面出现上一个
     *
     * @param count
     * @return 分页html
     */
    private String getYs(int count) {
        page = page == 0 ? 7 : page;  //第一次默认弟7页
        StringBuffer buffer = new StringBuffer();
        buffer.append("<div class=\"page\">");
        buffer.append("<span>共"+count+"条数据</span>");
        buffer.append("<select onchange=\"defaultYs(this.value)\"><option value=20>20</option><option value=50>50</option><option value=100>100</option></select>");
        buffer.append("<ul>");
        if (zys > pcys) {  //总页数大于页面上要显示的分页的数量
            int shang = (int) Math.ceil((float) pcys / 2);  //向上取整
            if (page <= shang) {  //当前的页数小于中间的那个页数    也就是只有下一页的情况
                for (int i = 1; i <= pcys; i++) {
                    if (i == page) {
                        buffer.append("<li class=\"bg\" onclick=\"paging(" + i + ")\">" + i + "</li>");
                    } else {
                        buffer.append("<li onclick=\"paging(" + i + ")\">" + i + "</li>");
                    }
                }
                buffer.append("<li onclick=\"paging(" + (page + 1) + ")\">下一页</li>");
            } else {
                int xia = (int) Math.floor((float) pcys / 2);  //向下取整
                if (page + xia < zys) {  //当前页加上需要在页面显示页数数量的一半  如果大于总页数  证明后面还有  正常拼出  上页下页都有
                    buffer.append("<li onclick=\"paging(" + (page - 1) + ")\">上一页</li>");
                    for (int i = xia; i >= 1; i--) {  //向左拼
                        buffer.append("<li onclick=\"paging(" + (page - i) + ")\">" + (page - i) + "</li>");
                    }
                    buffer.append("<li class=\"bg\" onclick=\"paging(" + page + ")\">" + page + "</li>");
                    for (int i = 1; i <= xia; i++) {   //向右拼
                        buffer.append("<li onclick=\"paging(" + (page + i) + ")\">" + (page + i) + "</li>");
                    }
                    buffer.append("<li onclick=\"paging(" + (page + 1) + ")\">下一页</li>");
                } else {  //当前页数加上一半小于总页数  从最后往左拼显示在页面上页数数量的个数   只有上一页的情况
                    buffer.append("<li>上一页</li>");
                    for (int i = pcys - 1; i >= 0; i--) {
                        if ((zys - i) == page) {
                            buffer.append("<li class=\"bg\" onclick=\"paging(" + (zys - i) + ")\">" + (zys - i) + "</li>");
                        } else {
                            buffer.append("<li onclick=\"paging(" + (zys - i) + ")\">" + (zys - i) + "</li>");
                        }
                    }
                }
            }
        } else {  //拼出所有的页数   也就是没有上页下页
            for (int i = 1; i <= zys; i++) {
                if (i == page) {
                    buffer.append("<li class=\"bg\" onclick=\"paging(" + i + ")\">" + i + "</li>");
                } else {
                    buffer.append("<li onclick=\"paging(" + i + ")\">" + i + "</li>");
                }
            }
        }
        buffer.append("&nbsp;跳至&nbsp;<input id=\"sen\" type=\"text\" onkeyup=tiao(this.value," + zys + ") />&nbsp;页<a href=\"javascript:;\" onclick=tiaoclick("+zys+")>go</a>");
        buffer.append("</ul></div>");
        return buffer.toString();
    }

    /**
     * 封装参数包括查询参数和分页的参数
     *
     * @param params
     * @return
     */
    public static Map<String, Object> getPageMap(Map<String, Object> params) {
        String pagebeginStr = null == params.get("pagebegin") ? "" : params.get("pagebegin").toString();
        String pagesizeStr = null == params.get("pagesize") ? "" : params.get("pagesize").toString();
        int pagebegins = "".equals(pagebeginStr) ? 1 : Integer.parseInt(pagebeginStr);  //第几页
        int pagesizes = "".equals(pagesizeStr) ? Integer.parseInt("20") : Integer.parseInt(pagesizeStr);  //每页多少条  如果要改变每页的数量
        params.put("pagebegin", pagebegins); //开始页数
        params.put("pagesize", pagesizes); //每页的条数
        params.put("pagestart", (pagebegins - 1) * pagesizes); //每页的条数
        return params;
    }
}
