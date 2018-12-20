package com.ssxu.util.aliyun;


import com.ssxu.entity.Ajax;
import com.ssxu.exception.MyException;
import com.ssxu.util.FileUtils;
import com.ssxu.util.StaticVariable;
import com.ssxu.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;


/**
 * 类描述：视屏上传公共类
 * 创建人：ssxu
 * 创建时间：2018-3-3 上午10:30:55
 *
 * @version 1.0
 */
@Controller
@PropertySource("classpath:aliyun.properties")
public class VideoUpload {

    @Value("${filepath:xss}")
    private String computerpath;
    @Value("${aliyunPath:xss}")
    private String aliyunPath;
    //最大文件大小
    @Value("${maxSize:xss}")
    private Long maxSize;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 文件上传
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Ajax<String> uploadFile(MultipartHttpServletRequest multiReq) {

        MultipartFile reqFile = multiReq.getFile("file");
        // 格式的校验key
        String dir = multiReq.getParameter("dir");
        String path = multiReq.getParameter("path");
        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<>();
        extMap.put("video", "mp4,avi,mpg,wmv,3gp,mov,mkv,asf,flv,rm,rmvb");
        extMap.put("wold", "doc,docx");
        extMap.put("img", "jpg,png");

        // 获取上传文件的路径
        String uploadFilePath = reqFile.getOriginalFilename();
        logger.info("uploadFlePath:" + uploadFilePath);
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(uploadFilePath.lastIndexOf('\\') + 1,
                uploadFilePath.indexOf('.'));
        //final String uploadFileName = StringUtils.getUUID();
        logger.info("multiReq.getFile()" + uploadFileName);
        // 截取上传文件的后缀
        final String uploadFileSuffix = uploadFilePath.substring(uploadFilePath.lastIndexOf('.') + 1,
                uploadFilePath.length());
        logger.info("uploadFileSuffix:" + uploadFileSuffix);

        if (StringUtils.isNotEmpty(dir)) {
            if (!Arrays.asList(extMap.get(dir).split(",")).contains(uploadFileSuffix)) {
                throw new MyException("上传视屏扩展名是不允许的扩展名。只允许" + extMap.get(dir) + "格式。");
            }
        }

        //检查文件大小
        /**if (reqFile.getSize() > maxSize) {
            throw new MyException("上传文件大小超过100M限制。");
        }*/
        // 阿里云返回视屏的名字
        String aliyunFileName;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        // 当前时间日期的字符串
        String nowDateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        final String mulu = computerpath + File.separator;
        String newFileName = mulu + uploadFileName + "." + uploadFileSuffix;
        File saveDirFile = new File(mulu);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        try {
            fis = (FileInputStream) reqFile.getInputStream();
            fos = new FileOutputStream(newFileName);
            byte[] temp = new byte[1024];
            int size=0;
            while((size=fis.read(temp))!=-1){
                fos.write(temp, 0, size);
            }
            // 上传阿里云
            aliyunFileName = Aliyun.uploadFile(uploadFileName + "." + uploadFileSuffix,
                    new File(newFileName), aliyunPath + path + nowDateStr + "/");
        } catch (IOException e) {
            e.printStackTrace();
            //return AjaxUtil.error("上传文件失败。");
            return new Ajax<>(StaticVariable.AJAXERROR, "上传文件失败。");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 删除本地视频
        FileUtils.deleteFile(mulu, uploadFileName + "." + uploadFileSuffix);
        // 更新进度删除数据
        ProgressSingleton.remove(uploadFileName);
        // 经过处理的视频  全部都转成了.mp4格式的
        //return AjaxUtil.success(aliyunFileName);
        return new Ajax<>(StaticVariable.AJAXSUCCESS, aliyunFileName);
    }
}
