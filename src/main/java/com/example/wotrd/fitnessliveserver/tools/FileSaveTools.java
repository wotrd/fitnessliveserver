package com.example.wotrd.fitnessliveserver.tools;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by wkj on 2017/9/12.
 * 用户本地文件存储
 */

public class FileSaveTools {

    /** 设置本地图片存储 */
    public static boolean setLocalPicSave(String content,String imgUrl) {
        try {
            File amatarWritefile=new File(imgUrl);
            byte[] bytes = Base64DecoderTools.stringToBytes(content);
            FileOutputStream outputStream = new FileOutputStream(amatarWritefile);
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /** 用户视频上传 */
    public static boolean uploadVideo(MultipartFile file,String videoUrl){
        try{
            File writefile = new File(videoUrl);
            FileOutputStream outputStream = new FileOutputStream(writefile);
            outputStream.write(file.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
