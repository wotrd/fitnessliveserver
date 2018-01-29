package com.example.wotrd.fitnessliveserver.manager.controller;

import com.example.wotrd.fitnessliveserver.manager.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统视频显示
 */
@Controller
@RequestMapping("/manager/videomanager")
public class VideoManagerController {
    @Autowired
    VideoService videoSevice;
    /**
     * 获取上传视频的进度值
     */
    @RequestMapping(value = "/getUploadVideoProgress")
    public void getUploadVideoProgress(HttpServletRequest request,HttpServletResponse response){
        videoSevice.getUploadVideoProgress(request, response);
    }
    /**
     * 上传视频
     */
    @RequestMapping(value = "/uploadSysVideo",method = RequestMethod.POST)
    public void uploadSysVideo(HttpServletRequest request,HttpServletResponse response){
        videoSevice.uploadSysVideo(request,response);
    }
    /**
     * 视频删除
     * @param
     */
    @RequestMapping(value = "/deleteVideo",method = RequestMethod.POST)
    @ResponseBody
    public void deleteVideo(HttpServletRequest request,HttpServletResponse response){
        videoSevice.deleteVideo(request,response);
    }
    /**
     * 系统视频显示
     */
    @RequestMapping("/sysvideo")
    public String sysVideoManager(){
        return "video-manager";
    }
    /**
     * 获取系统视频列表
     */
    @RequestMapping(value = "/querySysVideoList",method = RequestMethod.POST
            ,produces="application/json;charset=UTF-8")
    public void querySysVideoList(HttpServletRequest request, HttpServletResponse response){
        videoSevice.querySysVideoList(request,response);
    }
}
