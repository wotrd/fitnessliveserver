package com.example.wotrd.fitnessliveserver.manager.service;


import com.example.wotrd.fitnessliveserver.manager.dao.IUserDao;
import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/5/20.
 */
@Service
public class LoginService {

    private Map<String, Object> verifyMap=null;
    private Map<String, Object> loginMap=null;
    @Resource
    private IUserDao userDao;


    public Map<String, Object> checkLogin(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (null == loginMap){
            loginMap=new HashMap<>();
        }
        if (null == loginUser){
            loginMap.put("result","0");
            return loginMap;
        }else{
            loginMap.put("result","1");
            return loginMap;
        }
    }

    public Map<String, Object> doLogin(HttpServletRequest request,User loginUser) {
        if (null == loginMap){
            loginMap=new HashMap<>();
        }
        if (null==loginUser.getAccount()){
            loginMap.put("result","账户不能为空！");
            return loginMap;
        }else if (null==loginUser.getPassword()){
            loginMap.put("result","密码不能为空！");
            return loginMap;
        }else{
            loginUser=userDao.queryUserByAccountAndPassword(loginUser.getAccount(),loginUser.getPassword());
            if (null!=loginUser){
                Map<User,HttpSession> userMap= (Map<User, HttpSession>) request.
                        getServletContext().getAttribute("userMap");
                if (userMap.containsKey(loginUser)){
                    HttpSession httpSession = userMap.get(loginUser);
                    if (null!=httpSession.getAttribute("loginUser"))
                    {
                        httpSession.invalidate();
                    }
                }
                request.getSession().setAttribute("loginUser",loginUser);
                userMap.put(loginUser,request.getSession());
                loginMap.put("result","1");
            }else {
                loginMap.put("result",2);//输入不正确
            }
            return loginMap;
        }
    }
    public Map<String, Object> toVerifyCode(HttpServletRequest request) {
        String toVerifyCode = request.getParameter("verifyCode");
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        if (null!=toVerifyCode && null!=verifyCode && verifyCode.equalsIgnoreCase(toVerifyCode)) {
            if (null==verifyMap){
                verifyMap = new HashMap<>();
            }else if (!verifyMap.isEmpty()){
                verifyMap.clear();
            }
            verifyMap.put("result","1");
        }
        return verifyMap;
    }

    public void getAllUploadVideos() {
        List<UploadVideo> allUploadVideos = userDao.getAllUploadVideos();
        for (int i=0;i<allUploadVideos.size();i++){
            System.out.println(allUploadVideos.get(i).getUploadtime());
        }

    }
    /**
     *@param request
     * 后台用户退出登录
     */
    public void quitLogin( HttpServletRequest request) {
        Map<User,HttpSession> userMap =(Map<User, HttpSession>)request.getServletContext()
                .getAttribute("userMap");
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        if (userMap.containsKey(loginUser)){
            userMap.remove(loginUser);
        }
        request.getSession().invalidate();
        System.out.println("tuichu 成功");
    }
}
