package com.example.libraryserver.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.example.libraryserver.customer.mapper.UserMapper;
import com.example.libraryserver.customer.po.UserPo;
import com.example.libraryserver.tools.MessageSenderUtils;
import com.example.libraryserver.tools.ServletUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author wkj_pc
 * @date 2017/6/6
 */
@Slf4j
@Service
public class ManagerUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户列表
     *
     * @param request
     * @param response
     */
    public void queryUserList(HttpServletRequest request, HttpServletResponse response) {
        Integer pageNum = Integer.parseInt(request.getParameter("page")); // 取得当前页数,注意这是jqgrid自身的参数
        Integer pageSize = Integer.parseInt(request.getParameter("rows")); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String useraccount = request.getParameter("useraccount");
        String nickname = request.getParameter("usernickname");

        Page<Object> pageObj = PageHelper.startPage(pageNum, pageSize);
        List<UserPo> userPos = userMapper.getUserLikeAccountOrNickName(useraccount, nickname);

        JSONObject jo = new JSONObject();
        jo.put("rows", userPos);
        jo.put("total", pageObj.getPages());
        jo.put("records", pageObj.getTotal());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    /**
     * 删除用户
     *
     * @param request
     * @param response
     */
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        log.info("ids=" + ids);
        JSONObject result = new JSONObject();
        //删除操作
        int deleteResult = 0;
        try {
            deleteResult = userMapper.deleteByIds(ids.split(","));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        if (deleteResult > 0) {
            result.put("message", "用户信息删除成功!");
            result.put("flag", true);
        } else {
            result.put("message", "用户信息删除失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    public Map<String, Object> doRegister(HttpServletRequest request) {

        String password = request.getParameter("password");
        String phonenum = request.getParameter("phonenum");

        UserPo user = UserPo.builder().account(phonenum).mobileNum(phonenum).password(password)
                .nickName("Tom").gender(0).build();
        int insert = userMapper.insert(user);
        Map<String, Object> resultMap = new HashMap<>();
        if (insert > 0) {
            resultMap.put("result", 1);
        } else {
            resultMap.put("result", 0);
        }
        return resultMap;
    }


    public Map<String, Object> checkValidation(HttpServletRequest request, String validation) {
        String sendValidation = (String) request.getSession().getAttribute("validation");
        Map<String, Object> resultMap = new HashMap<>(2);
        if (null == sendValidation) {
            resultMap.put("result", 0);
        } else if (sendValidation.equals(validation)) {
            resultMap.put("result", 1);
        } else {
            resultMap.put("result", 0);
        }
        return resultMap;
    }

    public Map<String, Object> sendMessage(HttpServletRequest request, String number) {
        int code = (int) (Math.random() * (9999 - 1000)) + 1000;
        boolean result;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            result = MessageSenderUtils.sendMessage("你的手机正在注册我们健身直播系统，" +
                    "你的验证码是：" +
                    "" + code + " 非本人请忽视！", number);
        } catch (Exception e) {
            resultMap.put("result", 0);//失败
            return resultMap;
        }
        if (result) {
            request.getSession().setAttribute("validation", code);
            resultMap.put("result", 1);
        }
        return resultMap;
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String mobilenum = request.getParameter("mobilenum");
        String name = request.getParameter("name");
        boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String role = request.getParameter("role");
        String idcard = request.getParameter("idcard");
        if (verifyAccount(response, result, mobilenum)) {
            return;
        }
        UserPo userPo = userMapper.getByMobileNum(mobilenum);
        if (null != userPo) {
            result.put("message", "该账户已被使用!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        UserPo user = UserPo.builder().account(mobilenum).password("123456").name(name)
                .nickName(nickname).gender((sex) ? 1 : 0).email(email).idcard(idcard)
                .role(Integer.parseInt(role)).mobileNum(mobilenum).build();
        int insert = userMapper.insert(user);
        if (insert > 0) {
            result.put("message", "用户添加成功!");
            result.put("flag", true);
        } else {
            result.put("message", "用户添加失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private boolean verifyAccount(HttpServletResponse response, JSONObject result, String account) {
        if (StringUtils.isEmpty(account)) {
            result.put("message", "账户格式错误!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return true;
        }
        return false;
    }


    public void updateUser(HttpServletRequest request, HttpServletResponse response) {

        String uid = request.getParameter("uid");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String sex = request.getParameter("updatesex");
        String role = request.getParameter("role");
        String idcard = request.getParameter("idcard");
        String email = request.getParameter("email");
        String mobilenum = request.getParameter("mobilenum");
        String password = request.getParameter("password");
        JSONObject result = new JSONObject();

        if (StringUtils.isEmpty(password)) {
            result.put("message", "密码格式错误!");
            result.put("flag", false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        UserPo user = UserPo.builder().id(Long.parseLong(uid)).account(mobilenum)
                .name(name).nickName(nickname).password(password).gender(Integer.parseInt(sex))
                .email(email).idcard(idcard).mobileNum(mobilenum).role(Integer.parseInt(role))
                .build();
        int update = userMapper.update(user);
        if (update > 0) {
            result.put("message", "用户修改成功!");
            result.put("flag", true);
        } else {
            result.put("message", "用户修改失败!");
            result.put("flag", false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

}
