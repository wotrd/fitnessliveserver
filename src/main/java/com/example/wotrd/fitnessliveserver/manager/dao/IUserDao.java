package com.example.wotrd.fitnessliveserver.manager.dao;


import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/6/5.
 */

public interface IUserDao {
    boolean saveUser(User user);
    boolean updateUser(User user);
    boolean deleteUser();
    boolean deleteUserById(int id);
    boolean deleteUserByAccount(String account);
    User queryUserByAccount(String account);
    User queryUserById(int id);
    List<User> queryAdminAll();
    List<User> queryUserAll();
    User queryUserByPhonenum(String phonenum);
    User queryUserByIdcard(String idcard);
    User queryUserByEmail(String email);
    User queryUserByAccountAndPassword(String account, String password);

    List<UploadVideo> getAllUploadVideos();

    boolean addUser(User user);

    Page queryUserList(Map<String, Object> params);

    int deleteByIds(String ids);

    User queryUserByAccountExceptUid(String account, int uid);

    User queryUserByEmailExceptUid(String email, int uid);

    User queryUserByPhonenumExceptUid(String mobilenum, int uid);

    User queryUserByIdcardExceptUid(String idcard, int uid);

    boolean updateUserByUid(User user);
}
