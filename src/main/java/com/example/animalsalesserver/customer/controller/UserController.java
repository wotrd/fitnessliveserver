package com.example.animalsalesserver.customer.controller;

import com.example.animalsalesserver.conf.RespVo;
import com.example.animalsalesserver.customer.po.UserPo;
import com.example.animalsalesserver.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author wkj_pc
 * @date 2017/6/10
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @param account
     */
    @RequestMapping(value = "getUserInfo")
    @ResponseBody
    public RespVo getUserInfo(@RequestParam(value = "account", defaultValue = "") String account) {
        return userService.getUserByAccount(account);
    }

    /**
     * 修改用户信息
     */
    @ResponseBody
    @PostMapping(value = "updateUser")
    public RespVo updateUserInfo(@RequestBody UserPo userPo) {
        return userService.updateUser(userPo);
    }

    @ResponseBody
    @RequestMapping(value = "searchUser")
    public RespVo customerSearchUser(@RequestParam(value = "searchtext", defaultValue = "") String searchText) {
        return userService.userSearch(searchText);
    }

}
