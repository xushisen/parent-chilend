package com.ssxu.util.aliyun;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 
 * 类描述:阿里云上传
 *                 _     _                
 * __  ___   _ ___| |__ (_)___  ___ _ __  
 * \ \/ / | | / __| '_ \| / __|/ _ \ '_ \ 
 *  >  <| |_| \__ \ | | | \__ \  __/ | | |
 * /_/\_\\__,_|___/_| |_|_|___/\___|_| |_|
 *
 * 创建时间:2018-5-2 下午5:45:55
 * @version  1.0
 */
@Component
@PropertySource("classpath:aliyun.properties")
public class Aliyun {

	private static OSSClient ossClient;

    public static void main(String[] args) {
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("请输入路径：");
            String str = strin.readLine();
            System.out.println(uploadFile(fileName, new File(str), aliyunPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 上传操作手册   为了方便直接这里上传了  E*/

	private static String bucketName;
	private static String endpoint;
	private static String accessKeyId;
	private static String accessKeySecret;

	@Value("${bucketName}")
	public void setBucketName(String bucketName) {
		Aliyun.bucketName = bucketName;
	}
	@Value("${endpoint}")
	public void setEndpoint(String endpoint) {
		Aliyun.endpoint = endpoint;
	}
	@Value("${accessKeyId}")
	public void setAccessKeyId(String accessKeyId) {
		Aliyun.accessKeyId = accessKeyId;
	}
	@Value("${accessKeySecret}")
	public void setAccessKeySecret(String accessKeySecret) {
		Aliyun.accessKeySecret = accessKeySecret;
	}

	/**
	 * @param fileName 文件的名称
	 * @param  file 文件流
	 * @param  aliyunPath 阿里云的路径
	 * @return ${return_type} 阿里云对应的文件路径
	 */
	public static String uploadFile(String fileName, File file,
			String aliyunPath) throws OSSException, ClientException{
		String url = "";// 图片路劲
		// 创建OSSClient实例
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 断点续传上传
		try {
			// 带进度条的上传
			ossClient.putObject(new PutObjectRequest(bucketName, aliyunPath + fileName, file)
							.withProgressListener(new PutObjectProgressListener(fileName)));
			url = getUrl(aliyunPath + fileName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		// 关闭client
		ossClient.shutdown();
		return url;
	}

	/**
	 * 获得url链接
	 * 
	 * @param key 需要获取阿里云地址的文件名称
	 * @return 返回阿里云对应的文件
	 */
	public static String getUrl(String key) {
		String urls = "";
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
				* 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			urls = url.toString();
		}
		return urls.substring(0,urls.indexOf("?"));
	}

	/**
	 * aliyun下载
	 * @param key 要下载的文件
	 */
	public static void downloadFile(String key, HttpServletResponse response)
			throws OSSException, ClientException {
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			String fileName = key.substring(key.lastIndexOf("/") + 1);
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			//获取fileid对应的阿里云上的文件对象
			OSSObject ossObject = ossClient.getObject(bucketName, key);//bucketName需要自己设置
			// 读去Object内容  返回
			in = new BufferedInputStream(ossObject.getObjectContent());
			//System.out.println(ossObject.getObjectContent().toString());
			out = new BufferedOutputStream(response.getOutputStream());
			//通知浏览器以附件形式下载
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
			byte[] car = new byte[1024];
			int L = 0;
			while ((L = in.read(car)) != -1) {
				out.write(car, 0, L);
			}
			ossClient.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
