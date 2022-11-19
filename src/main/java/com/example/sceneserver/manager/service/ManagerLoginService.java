package com.example.sceneserver.manager.service;


import com.example.sceneserver.customer.mapper.UserMapper;
import com.example.sceneserver.customer.po.UserDO;
import com.example.sceneserver.customer.qo.UserLoginQo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wkj_pc
 * @date 2017/5/20
 */
@Slf4j
@Service
public class ManagerLoginService {

    @Resource
    private UserMapper userMapper;

    /**
     * 验证用户是否登陆
     *
     * @param request
     * @return
     */
    public Map<String, Object> checkLogin(HttpServletRequest request) {
        UserDO loginUser = (UserDO) request.getSession().getAttribute("loginUser");

        Map<String, Object> loginMap = new HashMap<>(2);
        if (null == loginUser) {
            loginMap.put("result", "0");
            return loginMap;
        } else {
            loginMap.put("result", "1");
            return loginMap;
        }
    }

    /**
     * 用户登陆
     *
     * @param request
     * @param userLoginQo
     * @return
     */
    public Map<String, Object> doLogin(HttpServletRequest request, UserLoginQo userLoginQo) {
        Map<String, Object> loginMap = new HashMap<>(2);
        if (null == userLoginQo.getAccount()) {
            loginMap.put("result", "账户不能为空！");
            return loginMap;
        } else if (null == userLoginQo.getPassword()) {
            loginMap.put("result", "密码不能为空！");
            return loginMap;
        } else {
            UserDO userDO = userMapper.getUserByAccount(userLoginQo.getAccount());
            if (null == userDO) {
                loginMap.put("result", "用户不存在！");
                return loginMap;
            } else if (!userDO.getPassword().equals(userLoginQo.getPassword())) {
                //输入不正确
                loginMap.put("result", 2);
                return loginMap;
            }
            Map<UserLoginQo, HttpSession> userMap = (Map<UserLoginQo, HttpSession>) request
                    .getServletContext().getAttribute("userLoginQo");

            request.getSession().setAttribute("loginUser", userLoginQo);
            userMap.put(userLoginQo, request.getSession());

            loginMap.put("result", "1");
            return loginMap;
        }
    }

    /**
     * 验证验证码
     *
     * @param request
     * @return
     */
    public Map<String, Object> toVerifyCode(HttpServletRequest request) {

        HashMap verifyMap = new HashMap<>(2);

        String toVerifyCode = request.getParameter("verifyCode");
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");

        if (null != toVerifyCode && null != verifyCode && verifyCode.equalsIgnoreCase(toVerifyCode)) {
            verifyMap.put("result", "1");
        }
        return verifyMap;
    }


    /**
     * @param request 后台用户退出登录
     */
    public void quitLogin(HttpServletRequest request) {

        Map<UserLoginQo, HttpSession> userMap = (Map<UserLoginQo, HttpSession>) request.getServletContext()
                .getAttribute("userLoginQo");

        UserLoginQo loginUser = (UserLoginQo) request.getSession().getAttribute("loginUser");

        if (userMap.containsKey(loginUser)) {
            userMap.remove(loginUser);
        }

        request.getSession().invalidate();

        log.info("推出登陆成功");

    }
}
