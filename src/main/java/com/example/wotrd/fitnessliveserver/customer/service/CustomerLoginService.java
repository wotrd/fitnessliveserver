package com.example.wotrd.fitnessliveserver.customer.service;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import com.alibaba.fastjson.JSON;
import com.example.wotrd.fitnessliveserver.customer.dao.ICustomerDao;
import com.example.wotrd.fitnessliveserver.manager.domain.Attention;
import com.example.wotrd.fitnessliveserver.manager.domain.Fans;
import com.example.wotrd.fitnessliveserver.manager.domain.SysMessage;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.FileSaveTools;
import com.example.wotrd.fitnessliveserver.tools.JPushTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wkj_pc on 2017/6/16.
 */
@Service
public class CustomerLoginService {

    @Autowired
    private ICustomerDao customerDao;
    @Autowired
    private Environment env;
    @Autowired
    private JPushClient jPushClient;
    private String sysVideos;

    public String qqLogin(User loginUser, HttpServletRequest request) {
        /* user.setAccount(openid); user.setToken("qq:"+token);user.setNickname
        user.setGender user.setAmatar*/
        if (!customerDao.checkUserByNameExist(loginUser.getAccount())){
            customerDao.registerQQUser(loginUser);
        }
        verifyUser(loginUser,request);
        User user = customerDao.getUserInfoByName(loginUser.getAccount());
        return JSON.toJSONString(user);
    }
    public String  customerLogin(User loginUser,HttpServletRequest request) {
        if ((!customerDao.checkUserExistByMobileNum(loginUser.getPhonenum()))){
            return "none";
        }
        if(!customerDao.getUserByMobileAndPwd(loginUser)){
            return "error";
        }
        verifyUser(loginUser,request);
        User user = customerDao.getUserInfoByMobile(loginUser.getPhonenum());
        return JSON.toJSONString(user);
    }
    public String weiboLogin(User loginUser, HttpServletRequest request) {
        if (!customerDao.checkUserByNameExist(loginUser.getAccount())){
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM--dd");
            loginUser.setBorndata(format.format(new Date()));
            customerDao.registerWeiboUser(loginUser);
        }
        User user = customerDao.getUserInfoByName(loginUser.getName());
        return JSON.toJSONString(user);
    }
    public boolean wechatLogin(User loginUser,HttpServletRequest request) {
        return false;
    }


    public void  verifyUser(User loginUser,HttpServletRequest request){
        //现将之前的登录销毁
        Map<User,HttpSession> userMap= (Map<User, HttpSession>) request.
                getServletContext().getAttribute("userMap");
        if (userMap.containsKey(loginUser)){
            HttpSession httpSession = userMap.get(loginUser);
            if (null!=httpSession)
            {
                httpSession.invalidate();
            }
        }
        request.getSession().setAttribute("loginUser",loginUser);
        userMap.put(loginUser,request.getSession());
    }
    public void quitLogin(User loginUser, HttpServletRequest request) {
        Map<User,HttpSession> userMap = (Map<User, HttpSession>) request.getServletContext()
                .getAttribute("userMap");
        if (userMap.containsKey(loginUser)){
            HttpSession httpSession = userMap.get(loginUser);
            httpSession.invalidate();
            userMap.remove(loginUser);
        }
    }

    public User getUserInfoByAccount(String account) {
        return customerDao.getUserInfoByAccount(account);
    }

    public String updateUserPassword(String mobilenum, String password) {
        return customerDao.updateUserPassword(mobilenum,password);
    }

    public String registerUser(String mobilenum, String password) {
        if (customerDao.checkUserExistByMobileNum(mobilenum)){
            return ":false";
        }
        return customerDao.registerUser(mobilenum,password);
    }

    public String  updateUserSexByAccount(String account, String content) {
        return (customerDao.updateUserSexByAccount(account,content))?"true:":"failed";
    }

    public String updateUserNicknameByAccount(String account, String content) {
       return (customerDao.updateUserNicknameByAccount(account,content))?"true:":"failed";
    }
    public String updateUserPersonalSignByAccount(String account, String content) {
        return (customerDao.updateUserPersonalSignByAccount(account,content))?"true:":"failed";
    }
    /**
     *  更新用户直播大图
     * @param content
     * @param account
     */
    public String updateUserLiveBigPicByAccount(String account, String content) {
        UUID uuid=UUID.randomUUID();
        String bigPicUrl=env.getProperty("fitnesslive_file_save_url")+"/img/livebigpic/"+account+uuid.toString()+".jpg";
        if (!FileSaveTools.setLocalPicSave( content,bigPicUrl))
            return "updatefailed";
        String getImageUrl=env.getProperty("fitnesslive_get_file_url")+"/img/livebigpic/"+account+uuid.toString()+".jpg";
        boolean b = customerDao.updateUserLiveBigPicByAccount(account, getImageUrl);
        return (b)?"true:"+getImageUrl:"updatefailed";
    }
    /**
     * 更新用户头像
     * @param account
     * @param content
     */
    public String updateUserAmatar(String account,String content) {
        UUID uuid = UUID.randomUUID();
        String amatarUrl=env.getProperty("fitnesslive_file_save_url")+"/img/amatar/"+account+uuid.toString()+".jpg";
        if (!FileSaveTools.setLocalPicSave( content,amatarUrl))
            return "updatefailed";
        String getImageUrl=env.getProperty("fitnesslive_get_file_url")+"/img/amatar/"+account+uuid.toString()+".jpg";
        boolean b = customerDao.updateUserAmatarByAccount(account, getImageUrl);
        return (b)?"true:"+getImageUrl:"updatefailed";
    }

    public List<Fans> getFansUserInfo(int uid) {
        return customerDao.getFansUserInfoById(uid);
    }

    public List<Attention> getAttentionUserInfo(int uid) {
        return customerDao.getAttentionUserInfoById(uid);
    }

    public List<Attention> getAttentionUserInfoByAccount(String account) {
        return customerDao.getAttentionUserInfoByIdByAccount(account);
    }

    public String pushMessageToPartAndroidClient(String content,String alias) {
        SysMessage message = getSysMessage(content,"server","",alias,"提醒");
        try{
            jPushClient.sendPush(JPushTools.buildAndroidAllMessageWithAlias
                    (JSON.toJSONString(message), alias));
            message.setResult("success");
            customerDao.addSysMessage(message);
            return "success";
        }catch (APIConnectionException e){
            // Connection error, should retry later
            //System.out.println("part- Connection error, should retry later");
            return "Connection error, should retry later";
        }catch (APIRequestException e) {
            // Should review the error, and fix the request
            //System.out.println("Error Message: " + e.getErrorMessage());
            return pushMessageToAllAndroidClient(content);
        }
    }

    public String pushMessageToAllAndroidClient(String content) {
        SysMessage message = getSysMessage(content,"server","","all","提醒");
        try{
            jPushClient.sendPush(JPushTools.buildAndroidAllMessage(JSON.toJSONString(message)));
            message.setResult("success");
            customerDao.addSysMessage(message);
            return "success";
            //System.out.println("---result="+result);
        }catch (APIConnectionException e){
            // Connection error, should retry later
            //System.out.println("Connection error, should retry later");
            return "Connection error, should retry later";
        }catch (APIRequestException e) {
            //System.out.println("Error Message: " + e.getErrorMessage());
            message.setResult(e.getErrorMessage());
            customerDao.addSysMessage(message);
            return "Should review the error, and fix the request";
        }
    }
    /**
     * 生成系统的推送信息
     * @param content
     * @param from
     * @param result
     * @param owner
     * @param title
     * @return
     */
    private SysMessage getSysMessage(String content, String from,String result,
                                     String owner,String title) {
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd-hh:MM:ss");
        SysMessage message=new SysMessage();
        message.setContent(content);
        message.setFrom(from);
        message.setIsRead(0);
        message.setOwner(owner);
        message.setResult(result);
        message.setTitle(title);
        message.setTime(format.format(new Date()));
        message.setTo(owner);
        return message;
    }
    /**
     * @param searchText
     */
    public String customerUserSearch(String searchText) {
        List<User> users = customerDao.customerSearchUser(searchText);
        if (null ==users)
        {
            return "";
        }else {
            return JSON.toJSONString(users);
        }
    }

    public String getSysVideos() {
        return JSON.toJSONString(customerDao.getSysVideos());
    }
}
