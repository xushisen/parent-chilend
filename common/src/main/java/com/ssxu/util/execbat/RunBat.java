package com.ssxu.util.execbat;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类描述：
 * 创建人：徐石森
 * 创建时间：2018/12/28  15:47
 *
 * @version 1.0
 */
public class RunBat {
    String path = "D:\\hehe\\IDEA2017\\code\\authority\\src\\main\\java\\com\\zakj\\util\\execbat\\";
    public void runbat(String batName) {
        String cmd = "cmd /c start " + path + batName;// pass
        try {
            Process ps = Runtime.getRuntime().exec(cmd);
            InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// 如果你不需要看输出，这行可以注销掉
            }
            in.close();
            ps.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("child thread donn");
    }

    public static void main(String[] args) {
        RunBat test1 = new RunBat();
        //test1.runbat("quanxian.bat");
        test1.runbat("shutdown.bat");
    }
}
