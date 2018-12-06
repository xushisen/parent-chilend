package com.ssxu.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * 类描述：关于io流的各种操作方法
 * <p>
 * 创建人：ssxu
 * 创建时间：2017-1-8 上午11:48:54
 * 版本 1.0.0
 */
public class FileUtils {

    /**
     * 读取文件
     *
     * @param filename
     * @return
     */
    public static String reanFile(String filename) {
        StringBuffer buffer = new StringBuffer();
        FileInputStream inputStream = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(new File(filename));
            isr = new InputStreamReader(inputStream, "utf-8");
            reader = new BufferedReader(isr);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                buffer.append(tempString + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 写入文件
     *
     * @param content
     * @param filename
     */
    public static void writerFileByLine(String content, String filename) {
        File file = new File(filename);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 下载txt文件内容是传过来的string
     *
     * @param response response
     * @param txt      要下载到txt的内容
     */
    public static void uploadText(HttpServletResponse response, String txt) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" + new Date().getTime() + ".txt");
            response.setContentType("text/plain;charset=utf-8");
            os.write(txt.getBytes());
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件
     *
     * @param path      路径不带名字
     * @param fileNames 所有的文件名字
     */
    public static void deleteFile(String path, String... fileNames) {
        if (fileNames.length > 0) {
            File file = null;
            for (String fileName : fileNames) {
                file = new File(path + File.separator + fileName);
                file.delete();
            }
        }
    }
}
