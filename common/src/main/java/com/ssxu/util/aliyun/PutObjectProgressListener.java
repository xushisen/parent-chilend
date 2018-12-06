package com.ssxu.util.aliyun;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;

/**
 * 
 * 类描述:阿里云上传进度类
 *                 _     _                
 * __  ___   _ ___| |__ (_)___  ___ _ __  
 * \ \/ / | | / __| '_ \| / __|/ _ \ '_ \ 
 *  >  <| |_| \__ \ | | | \__ \  __/ | | |
 * /_/\_\\__,_|___/_| |_|_|___/\___|_| |_|
 *
 * 创建时间:2018-5-2 下午5:46:06
 * @version  1.0
 */
public class PutObjectProgressListener implements ProgressListener {
	private long bytesWritten = 0;
	private long totalBytes = -1;

	// 是要放在key对应的值里面跟着进度一起返回的 为了区分现在上传的是视屏 还是图片
	private String fileNameKey;
	// 上传进度监听的key
	private String fileName;

	public PutObjectProgressListener(String fileNames){
		String[] nameKey = fileNames.split("\\.");
		this.fileName = nameKey[0];
		this.fileNameKey = nameKey[1];
	}

	@Override
	public void progressChanged(ProgressEvent progressEvent) {
		long bytes = progressEvent.getBytes();
		ProgressEventType eventType = progressEvent.getEventType();
		switch (eventType) {
		case TRANSFER_STARTED_EVENT:
			System.out.println("开始上传......");
			break;
		case REQUEST_CONTENT_LENGTH_EVENT:
			this.totalBytes = bytes;
			break;
		case REQUEST_BYTE_TRANSFER_EVENT:
			this.bytesWritten += bytes;
			if (this.totalBytes != -1) {
				int percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
				ProgressSingleton.put(fileName, fileNameKey +"!"+ percent);
			} else {
				System.out .println(bytes + " bytes have been written at this time, upload ratio: unknown"
								+ "(" + this.bytesWritten + "/...)");
			}
			break;
		case TRANSFER_COMPLETED_EVENT:
			System.out.println("上传完成, " + this.bytesWritten + " 字节共计上传");
			break;
		case TRANSFER_FAILED_EVENT:
			System.out.println("上传失败, " + this.bytesWritten + " 字节共计上传");
			break;
		default:
			break;
		}
	}
}
