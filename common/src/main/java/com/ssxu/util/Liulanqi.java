package com.ssxu.util;

import java.awt.*;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Map;


/**
 * 类描述：浏览器操作
 * 创建人：徐石森
 * 创建时间：2018/11/14  17:30
 *
 * @version 1.0
 */
public class Liulanqi {

    public static void browse1(String url) throws Exception {
        String osName = System.getProperty("os.name", "");// 获取操作系统的名字

        if (osName.startsWith("Windows")) {// windows
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        } else if (osName.startsWith("Mac OS")) {// Mac
            Class fileMgr = Class.forName("com.apple.eio.FileManager");
            Method openURL = fileMgr.getDeclaredMethod("openURL", String.class);
            openURL.invoke(null, url);
        } else {// Unix or Linux
            String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++) { // 执行代码，在brower有值后跳出，
                // 这里是如果进程创建成功了，==0是表示正常结束。
                if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                    browser = browsers[count];
                }
            }

            if (browser == null) {
                throw new RuntimeException("未找到任何可用的浏览器");
            } else {// 这个值在上面已经成功的得到了一个进程。
                Runtime.getRuntime().exec(new String[]{browser, url});
            }
        }
    }

    /**
     * @title 使用默认浏览器打开
     * @author Xingbz
     */
    public static void browse2(String url) throws Exception {
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }
    }

    /**
     * @title 通过获取环境变量的浏览器路径, 然后启动浏览器
     * @author Xingbz
     */
    public static void browse3(String url) throws Exception {
        String firefox = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
        Map map = System.getenv();
        for (Object key : map.keySet()) {
            String value = (String) map.get(key);
            if (value.contains("firefox.exe")) {
                firefox = value;
                break;
            }
        }
        Runtime.getRuntime().exec(new String[]{firefox, url});
    }

    /**
     * @title 启用cmd运行IE的方式来打开网址
     * @author Xingbz
     */
    public static void browse4(String url) throws Exception {
        Runtime.getRuntime().exec("cmd /c start " + url);//启用cmd运行默认浏览器
//        Runtime.getRuntime().exec("cmd /c start iexplore " + url);//启用cmd运行IE
    }

    /**
     * @title 利用java.lang.ProcessBuilder类创建系统进程的能力，通过浏览器地址启动浏览器，并将网址作为参数传送给浏览器。
     * ProcessBuilder类是J2SE1.5在java.lang中新添加的一个新类，此类用于创建操作系统进程，它提供一种启动和管理进程（也就是应用程序）的方法。
     * @author Xingbz
     */
    public static void browse5(String url) throws Exception {
        ProcessBuilder proc = new ProcessBuilder("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe",
                url);
        proc.start();
    }
}
