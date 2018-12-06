package com.ssxu.util;

import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 类描述：http 工具类
 * 创建人：徐石森
 * 创建时间：2018/11/29  11:33
 *
 * @version 1.0
 */
public class HttpUtils {
    /**
     * 模拟浏览器的请求
     *
     * @param httpURL 发送请求的地址
     * @param params  请求参数
     * @return 请求地址的返回值
     */
    public static String sendHttpRequest(String httpURL, Map<String, String> params) throws Exception {
        //建立URL连接对象
        URL url = new URL(httpURL);
        //创建连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求的方式(需要是大写的)
        conn.setRequestMethod("POST");
        //设置需要输出
        conn.setDoOutput(true);
        //判断是否有参数.
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            //sb.substring(1)去除最前面的&
            conn.getOutputStream().write(sb.substring(1).getBytes("utf-8"));
        }
        //发送请求到服务器
        conn.connect();
        //获取远程响应的内容.
        String responseContent = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("utf-8"));
        conn.disconnect();
        return responseContent;
    }

    /**
     * 模拟浏览器的请求
     *
     * @param httpURL    发送请求的地址
     * @param jesssionId 会话Id
     */
    public static void sendHttpRequest(String httpURL, String jesssionId) throws Exception {
        //建立URL连接对象
        URL url = new URL(httpURL);
        //创建连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求的方式(需要是大写的)
        conn.setRequestMethod("POST");
        //设置需要输出
        conn.setDoOutput(true);
        conn.addRequestProperty("Cookie", "JSESSIONID=" + jesssionId);
        //conn.getOutputStream().write(("token=" + token).getBytes("utf-8"));
        //发送请求到服务器
        conn.connect();
        conn.getInputStream();
        conn.disconnect();
    }

}
