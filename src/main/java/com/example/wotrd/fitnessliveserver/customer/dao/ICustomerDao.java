package com.example.wotrd.fitnessliveserver.customer.dao;


import com.example.wotrd.fitnessliveserver.manager.domain.*;

import java.util.List;

/**
 * Created by wkj_pc on 2017/6/17.
 */

public interface ICustomerDao {

    boolean checkUserByNameExist(String account);

    boolean registerQQUser(User loginUser);

    boolean getUserByAccountAndPwd(User loginUser);

    User getUserInfoByAccount(String account);

    boolean registerWeiboUser(User loginUser);

    int getFansNumberByAccount(String account);

    List<User> getAllLiveUserInfos();

    List<LiveTheme> getAllLiveThemes();

    boolean checkUserExistByMobileNum(String phonenum);

    boolean getUserByMobileAndPwd(User loginUser);

    User getUserInfoByMobile(String phonenum);

    String updateUserPassword(String mobilenum, String password);

    String registerUser(String mobilenum, String password);

    boolean updateUserAmatarByAccount(String account, String amatarUrl);

    boolean updateUserSexByAccount(String account, String content);

    boolean updateUserNicknameByAccount(String account, String content);

    boolean updateUserPersonalSignByAccount(String account, String content);

    boolean updateUserLiveBigPicByAccount(String account, String getImageUrl);

    User getUserInfoByName(String name);

    boolean updateUserLiveThemes(int uid, List<String> liveThemes);

    boolean uploadUserVideo(String title, String videourl, String thumbnail1, int uid);

    List<UploadVideo> getUserUploadVideoByUid(int uid);

    boolean setUserLiveStatusTagByAccount(int status, String account);

    User getLiveUserInfoByAccount(String account);

    String wsGetLiveUserAmatarByAccount(String account);

    List<User> wsGetWatcherInfoByAccount(String account);

    List<Fans> getFansUserInfoById(int uid);

    List<Attention> getAttentionUserInfoById(int uid);

    boolean setUserIsAttention(Attention attention, String type);

    List<Attention> getAttentionUserInfoByIdByAccount(String account);

    boolean addSysMessage(SysMessage message);

    List<User> customerSearchUser(String searchText);

    List<UploadVideo> getSysVideos();
}