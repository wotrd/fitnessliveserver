package com.example.sceneserver.customer.service;

import com.example.sceneserver.conf.RespVo;
import com.example.sceneserver.customer.mapper.UserMapper;
import com.example.sceneserver.customer.po.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wkj_pc
 * @date 2017/6/16
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过account查询用户
     *
     * @param account
     * @return
     */
    public RespVo getUserByAccount(String account) {
        UserDO userDO = userMapper.getUserByAccount(account);
        return RespVo.builder().code("200").msg("success").data(userDO).build();
    }

    /**
     * 更新用户
     *
     * @param userDO
     * @return
     */
    public RespVo updateUser(UserDO userDO) {
        userMapper.update(userDO);
        return RespVo.builder().code("200").msg("success").build();
    }

    /**
     * @param searchText
     */
    public RespVo userSearch(String searchText) {
        List<UserDO> users = userMapper.getUserLikeAccount(searchText);
        if (null == users || users.size() == 0) {
           users = new ArrayList<>();
        }
        return RespVo.builder().code("200").msg("success").data(users).build();
    }


}
