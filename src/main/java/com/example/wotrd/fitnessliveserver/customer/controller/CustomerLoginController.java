package com.example.wotrd.fitnessliveserver.customer.controller;

import com.alibaba.fastjson.JSON;
import com.example.wotrd.fitnessliveserver.customer.service.CustomerLoginService;
import com.example.wotrd.fitnessliveserver.manager.domain.Attention;
import com.example.wotrd.fitnessliveserver.manager.domain.Fans;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by wkj_pc on 2017/6/10.
 */

@Controller
@RequestMapping("/customer/login")
public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;

    /**
     * 获取系统的视频
     */
    @RequestMapping(value = "/getSysVideos")
    @ResponseBody
    public String getSysVideos(){
        return customerLoginService.getSysVideos();
    }

    /**
     * 服务端进行极光推送,推送给全部android设备
    */
    @RequestMapping(value = "/pushMessageToAllAndroidClient")
    @ResponseBody
    public String pushMessageToAllAndroidClient(@RequestParam(value = "content",defaultValue = "")String content){
        return customerLoginService.pushMessageToAllAndroidClient(content);
    }
    /**
     * 服务端进行极光推送,推送给全部android设备
     */
    @RequestMapping(value = "/pushMessageToPartAndroidClient")
    @ResponseBody
    public String pushMessageToPartAndroidClient(@RequestParam(value = "uid",defaultValue = "")String uid){
        return customerLoginService.pushMessageToPartAndroidClient("nihao",uid);
    }
    /**
     * @param request
     * @param user
     * @return
     * 执行登录操作
     */
    @ResponseBody
    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    public String login(@RequestParam(value = "user", defaultValue = "")String user ,
                        HttpServletRequest request){
        String  result="";
        User loginUser = JSON.parseObject(user, User.class);
        if (loginUser.getToken().substring(0,3).contains("qq:")){
           result =customerLoginService.qqLogin(loginUser,request);
        }else if (loginUser.getToken().substring(0,3).contains("wx:")){
           result=String.valueOf(customerLoginService.wechatLogin(loginUser,request));
        }else if (loginUser.getToken().substring(0,3).contains("wb:")){
            result=customerLoginService.weiboLogin(loginUser,request);
        }else {
            result=customerLoginService.customerLogin(loginUser,request);
        }
        return result;
    }
    /** 获取用户信息
     * @param account
     * */
    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public String getUserInfo(@RequestParam(value = "account",defaultValue = "")String account){
        User loginUser = customerLoginService.getUserInfoByAccount(account);
        return JSON.toJSONString(loginUser);
    }

    /**
     * 获取直播用户粉丝的信息，并返回
     * @param uid
     */
    @RequestMapping(value = "/getFansUserInfo")
    @ResponseBody
    public String getFansUserInfo(@RequestParam(value = "uid")String uid) {
        List<Fans> fansUsers = customerLoginService.getFansUserInfo(Integer.parseInt(uid));
        return JSON.toJSONString(fansUsers);
    }
    /**
     * 获取直播用户关注用户的信息，并返回
     * @param uid
     */
    @RequestMapping(value = "/getAttentionUserInfo")
    @ResponseBody
    public String getAttentionUserInfo(@RequestParam(value = "uid")String uid) {
        List<Attention> attentionUsers = customerLoginService.getAttentionUserInfo(Integer.parseInt(uid));
        return JSON.toJSONString(attentionUsers);
    }
    /**
     * 通过account获取直播用户关注用户的信息，并返回
     * @param account
     */
    @RequestMapping(value = "getAttentionUserInfoByAccount",method = RequestMethod.POST)
    @ResponseBody
    public String getAttentionUserInfoByAccount(@RequestParam(value = "account",defaultValue = "")String account){
        List<Attention> attentionUsers = customerLoginService.getAttentionUserInfoByAccount(account);
        return JSON.toJSONString(attentionUsers);
    }
    /**
     * 退出登录
     * @param user
     * @param request
     */
    @RequestMapping(value = "/quitLogin")
    @ResponseBody
    public String quitLogin(@RequestParam(value = "user", defaultValue = "") String user, HttpServletRequest request){
        System.out.println("你开始退出了！");
        User loginUser = JSON.parseObject(user, User.class);
        if (null==loginUser)
            return "";
        customerLoginService.quitLogin(loginUser,request);
        return "";
    }

    /** 返回客户端请求的验证码 */
    @RequestMapping(value = "/getVerifycode")
    @ResponseBody
    public String getVerifycode(){
        return "true:1234";
    }

    /** 修改用户密码*/
    @ResponseBody
    @RequestMapping(value = "/updateUserPassword")
    public String updateUserPassword(@RequestParam(value = "mobilenum",defaultValue = "")String mobilenum ,
                                     @RequestParam(value = "password",defaultValue = "")String password ){
        return customerLoginService.updateUserPassword(mobilenum,password);
    }
    /** 用户注册*/
    @RequestMapping(value = "/registerUser")
    @ResponseBody
    public String registerUser(@RequestParam(value = "mobilenum",defaultValue = "")String mobilenum ,
                               @RequestParam(value = "password",defaultValue = "")String password ){
        return customerLoginService.registerUser(mobilenum,password);
    }

    /** 修改用户信息*/
    @ResponseBody
    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public String updateUserInfo(HttpServletRequest request) throws IOException {
        String account = request.getParameter("account");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        if (type.equals("amatar")){ //头像
            return customerLoginService.updateUserAmatar(account,content);
        }else if (type.equals("sex")){
            return customerLoginService.updateUserSexByAccount(account,content);
        }else if (type.equals("nickname")){
            return customerLoginService.updateUserNicknameByAccount(account,content);
        }else if (type.equals("personalsign")){
            return customerLoginService.updateUserPersonalSignByAccount(account,content);
        }else if (type.equals("livebigimg")){       //直播大图
            return customerLoginService.updateUserLiveBigPicByAccount(account,content);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/customerSearchUser")
    public String customerSearchUser(@RequestParam(value = "searchtext",defaultValue = "") String searchText,
                                     @RequestParam(value = "account",defaultValue = "") String account){
        if (TextUtils.isEmpty(searchText) || TextUtils.isEmpty(account)) return "";
        return customerLoginService.customerUserSearch(searchText,account);
    }
}
