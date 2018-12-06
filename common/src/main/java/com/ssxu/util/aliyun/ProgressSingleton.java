package com.ssxu.util.aliyun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;

/**
 * 
 * 类描述:上传进度监听类
 *                 _     _                
 * __  ___   _ ___| |__ (_)___  ___ _ __  
 * \ \/ / | | / __| '_ \| / __|/ _ \ '_ \ 
 *  >  <| |_| \__ \ | | | \__ \  __/ | | |
 * /_/\_\\__,_|___/_| |_|_|___/\___|_| |_|
 *
 * 创建时间:2018-5-8 上午8:32:42
 * @version  1.0
 */
@Controller
public class ProgressSingleton {

	 //为了防止多用户并发，使用线程安全的Hashtable
	 private static Hashtable<String,String> table = new Hashtable<>();
	 
	 public static void put(String key, String value){
		 table.put(key, value);
	 }
	 
	 @RequestMapping("/ProgressSingleton")
	 @ResponseBody()
	 public static Object get(String key){
		 return table.get(key);
	 }
	 
	 public static Object remove(String key){
		 return table.remove(key);
	 }
}
