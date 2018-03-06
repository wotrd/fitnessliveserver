package com.example.wotrd.fitnessliveserver.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.example.wotrd.fitnessliveserver.manager.dao.VideoDao;
import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.tools.DateUtil;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.ServletUtil;
import com.example.wotrd.fitnessliveserver.tools.VideoThumbnailUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;

@Service
public class VideoService {

    @Autowired
    private VideoDao videoDao;
    @Autowired
    private Environment env;
    /**
     *  视频删除
     *  @param request
     *  @param response
     */
    public void deleteVideo(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        JSONObject result = new JSONObject();
        //通过id获取视频列表，并且将每个本地视频进行删除
        List<UploadVideo> videos = videoDao.getVideoListByIds(ids);
        if (null!=videos&&videos.size()>0){
            String fileUrl=env.getProperty("fitnesslive_file_save_url");
            for (int i=0;i<videos.size();i++){
                UploadVideo uploadVideo = videos.get(i);
                try {
                    String thumbnailurl = uploadVideo.getThumbnailurl();
                    int index = thumbnailurl.indexOf('m');
                    String thumbnail = fileUrl+thumbnailurl.substring(index-2);
                    File thumbnailFile = new File(thumbnail);
                    if (thumbnailFile.exists()){
                        thumbnailFile.delete();
                    }
                    String videoUrl=uploadVideo.getVideourl();
                    String basevideoUrl=fileUrl+videoUrl.substring(index-2);
                    File videoFile=new File(basevideoUrl);
                    if (videoFile.exists()){
                        videoFile.delete();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    result.put("message","服务器繁忙，请稍后...");
                    result.put("flag",true);
                    ServletUtil.createSuccessResponse(200, result, response);
                    return;
                }
            }
        }else {
            result.put("message","服务器繁忙，请稍后...");
            result.put("flag",true);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        //删除视频操作
        int index = videoDao.deleteByIds(ids);
        if(index>0){
            result.put("message","视频删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","视频删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 获取系统视频列表
     */
    public void querySysVideoList(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("title", title);
        if(null!=type){
            if (TextUtils.isEmpty(type)){
                params.put("type", "");
            }else if (type.equals("系")||type.equals("统")||type.equals("系统")){
                params.put("type", "0");
            }else if (type.equals("用")||type.equals("户")||type.equals("用户")){
                params.put("type", "1");
            }else {
                params.put("type", "2");    //type==2代表让搜索不到
            }
        }
        Page pageObj= videoDao.querySysVideoList(params);
        List<Map<String, Object>> msgList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", msgList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }
    /**
     * 获取上传视频的进度值
     */
    public void getUploadVideoProgress(HttpServletRequest request,HttpServletResponse response){
        System.out.println("---start get upload progress");
        String progress = (String) request.getSession().getAttribute("upload-sys-video-progress");
        if(null==progress||"".equals(progress)) return;
        JSONObject result=new JSONObject();
        result.put("progress",(null!=progress)?progress:0);
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200, result, response);
        return;
    }
    /**
     * 上传系统视频
     */
    public void uploadSysVideo(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        JSONObject result=new JSONObject();
        System.out.println("--------upload video start");
        /** 设置本地存储地址和名字*/
        String baseUrl = env.getProperty("fitnesslive_file_save_url");
        String baseRemoteUrl=env.getProperty("fitnesslive_get_file_url");
        UUID uuid = UUID.randomUUID();
        String thumbnailName="/img/media/pic/"+uuid.toString()+".jpg";
        String videoName="/img/media/video/"+uuid.toString()+".mp4";
         /** 视频上传 */
        System.out.println("----------video start upload");
        long time=System.currentTimeMillis();

         if (!saveVideoTodisk("upload_file",baseUrl+videoName,result,request,response)){
             System.out.println("--------video upload to disk");
            result.put("message","服务器繁忙，请稍后...");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
         }
        System.out.println("-----------video upload success time "+(System.currentTimeMillis()-time));
        /** 用户缩略图上传*/
        try {
            System.out.println("----------video img start upload");
            if (!VideoThumbnailUtils.fetchFrame(baseUrl+videoName,baseUrl+thumbnailName)){
                result.put("message","服务器繁忙，请稍后...");
                result.put("flag",false);
                ServletUtil.createSuccessResponse(200, result, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("---------video img save failed");
            result.put("message","服务器繁忙，请稍后...");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        /** 将上传的记录添加到数据库中 */
        System.out.println("------------upload video img time"+(System.currentTimeMillis()-time));
        if (!videoDao.uploadSysVideo(title, baseRemoteUrl+videoName,
                baseRemoteUrl+thumbnailName, DateUtil.formatNormalDateString(new Date()),0,1)){
            /**删除上传的视频缩略图*/
            File picFile = new File(baseUrl + thumbnailName);
            if (picFile.exists()){
                picFile.delete();
            }
            /**删除上传的视频*/
            File videoFile = new File(baseUrl + videoName);
            if (videoFile.exists()){
                videoFile.delete();
            }
            System.out.println("---------save data failed");
            result.put("message","服务器繁忙，请稍后...");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
        }
        result.put("message","上传成功！");
        result.put("flag",true);
        ServletUtil.createSuccessResponse(200, result, response);
    }
    /**
     * 上传视频到文件
     */
    public boolean saveVideoTodisk(String partName, String url, JSONObject result,
                                   HttpServletRequest request, HttpServletResponse response) {
        File file = new File(url);
        InputStream inputStream=null;
        FileOutputStream ostream=null;
        try {
            long count=0;
            long size=0;
            Part part= request.getPart(partName);
            size = part.getSize();
            int len=0;
            inputStream = part.getInputStream();
            byte[] buff=new byte[2048];
            ostream = new FileOutputStream(file);
            while ((len= inputStream.read(buff))!=-1){
                ostream.write(buff,0,len);
                count+=len;
                request.getSession().setAttribute("upload-sys-video-progress",
                        getNumberFormat(count,size)+"%");
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if (null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=ostream){
                try {
                    ostream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 设置百分比
     */
    public  String getNumberFormat(long num1,long num2){
        NumberFormat numberFormat=NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(0);
        return numberFormat.format((float) num1 / (float) num2*100);
    }
}
