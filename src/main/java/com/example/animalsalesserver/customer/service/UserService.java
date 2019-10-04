package com.example.animalsalesserver.customer.service;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.mapper.UserMapper;
import com.example.animalsalesserver.customer.po.UserPo;
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
        UserPo userPo = userMapper.getUserByAccount(account);
        return RespVo.builder().code("200").msg("success").data(userPo).build();
    }

    /**
     * 更新用户
     *
     * @param userPo
     * @return
     */
    public RespVo updateUser(UserPo userPo) {
        userMapper.update(userPo);
        return RespVo.builder().code("200").msg("success").build();
    }

    /**
     * @param searchText
     */
    public RespVo userSearch(String searchText) {
        List<UserPo> users = userMapper.getUserLikeAccount(searchText);
        if (null == users || users.size() == 0) {
           users = new ArrayList<>();
        }
        return RespVo.builder().code("200").msg("success").data(users).build();
    }


}
