package com.example.wotrd.fitnessliveserver.customer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.wotrd.fitnessliveserver.customer.service.CustomerLiveChattingService;
import com.example.wotrd.fitnessliveserver.manager.domain.LiveTheme;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * Created by wkj_pc on 2017/8/22.
 */
@Controller
@RequestMapping("/customer/live")
public class CustomerLiveController {

    @Autowired
    CustomerLiveChattingService liveService;
    /**
     * 获取全部直播用户的信息，并返回
     */
    @RequestMapping(value = "/getHomeLiveUserInfos")
    @ResponseBody
    public String getHomeLiveUserInfos() {
        List<User> liveUsers = liveService.getAllLiveUserInfo();
        return JSON.toJSONString(liveUsers);
    }
    /**
     * 获取直播用户的信息，并返回
     */
    @RequestMapping(value = "/getLiveUserInfo")
    @ResponseBody
    public String getLiveUserInfo(@RequestParam(value = "account")String account) {
        User liveUser = liveService.getLiveUserByAccount(account);
        return JSON.toJSONString(liveUser);
    }

    /**
     * 获取全部直播用户的直播风格，并返回
     */
    @RequestMapping(value = "/getHomeUserLiveThemes")
    @ResponseBody
    public String getHomeUserLiveThemes() {
        List<LiveTheme> liveThemes = liveService.getUserLiveThemes();
        return JSON.toJSONString(liveThemes);
    }

    /**
     * 添加直播用户的直播风格
     */
    @RequestMapping(value = "/updateLiveUserStyle")
    @ResponseBody
    public String addLiveUserStyle(@RequestParam(value = "uid", defaultValue = "") String uid,
                                   @RequestParam(value = "livethemes", defaultValue = "") String livethemes) {
        List<String> themes = JSON.parseObject(livethemes, new TypeReference<List<String>>(){});
        return liveService.updateLiveUserStyle(Integer.parseInt(uid),themes);
    }

    /**用户视频上传*/
    @RequestMapping(value = "/uploadRecoderVideo")
    @ResponseBody
    public String uploadRecoderVideo(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "title")String title,
                                     @RequestParam(value = "uid")String uid,@RequestParam(value = "thumbnail")String thumbnail){
        return liveService.uploadUserVideos(file,title,Integer.parseInt(uid),thumbnail);
    }
    /** 通过ID获取用户直播的视频*/
    @RequestMapping(value = "/getUserUploadVideo")
    @ResponseBody
    public String getUserUploadVideo(@RequestParam(value = "uid")String uid){
        return liveService.getUserUploadVideoByUid(Integer.parseInt(uid));
    }
    /** 通过账户获取用户直播的视频*/
    @RequestMapping(value = "/getUserUploadVideos")
    @ResponseBody
    public String getUserUploadVideos(@RequestParam(value = "account")String account){
        User user = liveService.getLiveUserByAccount(account);
        return liveService.getUserUploadVideoByUid(user.getUid());
    }
    /** 关闭直播状态*/
    @RequestMapping(value = "/closeLiveStatus")
    @ResponseBody
    public void closeLiveStatus(@RequestParam(value = "account")String account){
       liveService.setLiveStatusClosed(account);
    }
    /** 设置用户是否关注 */
    @RequestMapping(value = "/setUserIsAttention")
    @ResponseBody
    public String setUserIsAttention(@RequestParam(value = "attention")String attention,
                                   @RequestParam(value = "type")String type){
        return liveService.setUserIsAttention(attention,type);
    }

}