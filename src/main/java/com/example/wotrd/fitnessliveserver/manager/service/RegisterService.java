package com.example.wotrd.fitnessliveserver.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.example.wotrd.fitnessliveserver.manager.dao.IUserDao;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.MessageSenderUtils;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.ServletUtil;
import com.example.wotrd.fitnessliveserver.tools.VerifyUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wkj_pc on 2017/6/6.
 */
@Service
public class RegisterService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private Environment env;

    private Map<String,Object> resultMap=new HashMap<>();
    public Map<String, Object> doRegister(HttpServletRequest request) {
        User user=new User();
        user.setAccount(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));
        user.setNickname(request.getParameter("nickname"));
        user.setGender(request.getParameter("sex"));
        user.setEmail(request.getParameter("email"));
        user.setIdcard(request.getParameter("idcard"));
        user.setPhonenum(request.getParameter("phonenum"));
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        user.setBorndata(format.format(new Date()));
        if (userDao.saveUser(user)){
            resultMap.put("result",1);
        }else {
            resultMap.put("result",0);
        }
        return resultMap;
    }

    public Map<String,Object> checkAccount(String account) {
        if (null==account){
            resultMap.put("result",2);  //不正确
        }
        if (null==userDao.queryUserByAccount(account)){
            resultMap.put("result",1);  //合格
        }else {
            resultMap.put("result",2);
        }
        return resultMap;
    }

    public Map<String,Object> checkPhonenum(String phonenum) {
        if (!VerifyUtils.verifyPhonenum(phonenum))
        {
            resultMap.put("result",2);  //不合格
        }
        if (null==userDao.queryUserByPhonenum(phonenum)){
            resultMap.put("result",1);
        }else {
            resultMap.put("result",3);//已存在
        }
        return resultMap;
    }

    public Map<String,Object> checkIdcard(String idcard) {
        if (VerifyUtils.verifyIdcard(idcard)){
            resultMap.put("result",2);//不合格
        }
        if (null==userDao.queryUserByIdcard(idcard)){
            resultMap.put("result",1);
        }else {
            resultMap.put("result",3);//已经存在
        }
        return resultMap;
    }

    public Map<String,Object> checkEmail(String email) {
        if (VerifyUtils.verifyEmail(email)){
            resultMap.put("result",2);//不合格
        }
        if (null==userDao.queryUserByEmail(email)){
            resultMap.put("result",1);
        }else {
            resultMap.put("result",3);//已经存在
        }
        return resultMap;
    }

    public Map<String,Object> checkValidation(HttpServletRequest request,String validation) {
        String sendValidation = (String) request.getSession().getAttribute("validation");
        if (null==sendValidation){
            resultMap.put("result",0);
        }else if (sendValidation.equals(validation)){
            resultMap.put("result",1);
        }else {
            resultMap.put("result",0);
        }
        return resultMap;
    }
    public Map<String,Object> sendMessage(HttpServletRequest request,String number) {
        int code=(int)(Math.random()*(9999-1000))+1000;
        boolean result=false;
        try {
            result= MessageSenderUtils.sendMessage("你的手机正在注册我们健身直播系统，" +
                    "你的验证码是：" +
                    "" + code+" 非本人请忽视！", number);
        }catch (Exception e){
            resultMap.put("result",0);//失败
            return resultMap;
        }
        if (result){
            request.getSession().setAttribute("validation",code);
            resultMap.put("result",1);
        }
        return resultMap;
    }

    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        JSONObject result=new JSONObject();
        String mobilenum = request.getParameter("mobilenum");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String email = request.getParameter("email");
        String nickname = request.getParameter("nickname");
        String role = request.getParameter("role");
        String idcard = request.getParameter("idcard");
        String account = request.getParameter("account");
        if (verifyAccount(response, result, account)) return;
        if (null!=userDao.queryUserByAccount(account)){
            result.put("message","该账户已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        if (verifyEmail(response, email, result)) return;
        if (null!=userDao.queryUserByEmail(email)){
            result.put("message","该邮箱已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        if (verifyMobile(response, mobilenum, result)) return;
        if (null!=userDao.queryUserByPhonenum(mobilenum)){
            result.put("message","该手机号已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        if (verifyIdcard(response, result, idcard)) return;
        if (null!=userDao.queryUserByIdcard(idcard)){
            result.put("message","该身份证已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        User user=new User();
        user.setAccount(account);
        user.setPassword("666666");
        user.setName(name);
        user.setNickname(nickname);
        user.setGender(sex);
        user.setEmail(email);
        user.setIdcard(idcard);
        user.setRole(Integer.parseInt(role));
        user.setPhonenum(mobilenum);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM--dd");
        user.setBorndata(format.format(new Date()));
        if(userDao.addUser(user)){
            result.put("message","用户添加成功!");
            result.put("flag",true);
        }else {
            result.put("message","用户添加失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private boolean verifyIdcard(HttpServletResponse response, JSONObject result, String idcard) {
        if(!VerifyUtils.verifyIdcard(idcard)){
            result.put("message","身份证格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return true;
        }
        return false;
    }

    private boolean verifyAccount(HttpServletResponse response, JSONObject result, String account) {
        if (TextUtils.isEmpty(account)){
            result.put("message","账户格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return true;
        }
        return false;
    }

    public void queryUserList(HttpServletRequest request,HttpServletResponse response) {
        String page = request.getParameter("page"); // 取得当前页数,注意这是jqgrid自身的参数
        String rows = request.getParameter("rows"); // 取得每页显示行数，,注意这是jqgrid自身的参数
        String useraccount = request.getParameter("useraccount");
        String nickname = request.getParameter("usernickname");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("useraccount", useraccount);
        params.put("usernickname", nickname);
        Page pageObj= userDao.queryUserList(params);
        List<Map<String, Object>> userList=pageObj.getResultList();
        JSONObject jo=new JSONObject();
        jo.put("rows", userList);
        jo.put("total", pageObj.getTotalPages());
        jo.put("records", pageObj.getTotalRows());
        ServletUtil.createSuccessResponse(200, jo, response);
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String ids = request.getParameter("ids");
        System.out.println("ids="+ids);
        JSONObject result = new JSONObject();
        //删除操作
        int index = userDao.deleteByIds(ids);
        if(index>0){
            result.put("message","用户信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","用户信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String uid = request.getParameter("uid");
        Part amatarPart = request.getPart("amatar");
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");
        String borndate = request.getParameter("borndate");
        String sex = request.getParameter("updatesex");
        String role = request.getParameter("role");
        String grade = request.getParameter("grade");
        String personalsign = request.getParameter("personalsign");
        String idcard = request.getParameter("idcard");
        String email = request.getParameter("email");
        String mobilenum = request.getParameter("mobilenum");
        String password = request.getParameter("password");
        String verifypassword = request.getParameter("verifypassword");
        JSONObject result = new JSONObject();
        //修改操作
        UUID uuid = UUID.randomUUID();
        String amatarUrl=env.getProperty("fitnesslive_img_save_url")+"/img/amatar/"+uid+uuid.toString()+".jpg";
        if (amatarPart.getSize()>0){
            //头像上传
            if (uploadPicByPart(amatarPart, amatarUrl)){
                result.put("message","服务器繁忙，请稍后再试...");
                result.put("flag",false);
            }
        }
        //验证信息
        if (verifyAccount(response, result, account)) return;
        if (null!=userDao.queryUserByAccountExceptUid(account,Integer.parseInt(uid))){
            result.put("message","该账户已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        if (verifyEmail(response, email, result)) return;
        if (null!=userDao.queryUserByEmailExceptUid(email,Integer.parseInt(uid))){
            result.put("message","该邮箱已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return ;
        }
        if (verifyMobile(response, mobilenum, result)) return;
        if (null!=userDao.queryUserByPhonenumExceptUid(mobilenum,Integer.parseInt(uid))){
            result.put("message","该手机号已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (verifyIdcard(response, result, idcard)) return;
        if (null!=userDao.queryUserByIdcardExceptUid(idcard,Integer.parseInt(uid))){
            result.put("message","该身份证已被使用!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (TextUtils.isEmpty(password)){
            result.put("message","密码格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (TextUtils.isEmpty(verifypassword)){
            result.put("message","验证密码格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if (!password.equals(verifypassword)){
            result.put("message","密码输入不一致!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        User user=new User();
        user.setUid(Integer.parseInt(uid));
        user.setAccount(account);
        user.setName(name);
        user.setNickname(nickname);
        user.setPassword(password);
        if (Integer.parseInt(sex)==1){
            user.setGender("男");
        }else {
            user.setGender("女");
        }
        user.setEmail(email);
        user.setIdcard(idcard);
        user.setPhonenum(mobilenum);
        user.setPersonalsign(personalsign);
        user.setRole(Integer.parseInt(role));
        user.setGrade(Integer.parseInt(grade));
        user.setPassword(password);
        user.setBorndata(borndate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
        user.setCreatetime(format.format(new Date()));
        String getImageUrl=env.getProperty("get_img_url")+"/img/amatar/"+uid+uuid.toString()+".jpg";
        user.setAmatar(getImageUrl);
        if(userDao.updateUserByUid(user)){
            result.put("message","用户修改成功!");
            result.put("flag",true);
        }else {
            result.put("message","用户修改失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    private boolean verifyMobile(HttpServletResponse response, String mobilenum, JSONObject result) {
        if (!VerifyUtils.verifyPhonenum(mobilenum)){
            result.put("message","手机号格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return true;
        }
        return false;
    }

    private boolean verifyEmail(HttpServletResponse response, String email, JSONObject result) {
        if(!VerifyUtils.verifyEmail(email)){
            result.put("message","邮箱格式错误!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return true;
        }
        return false;
    }

    /**
     * 图片上传
     * @param amatarUrl
     * @param part
     */
    private boolean uploadPicByPart(Part part, String amatarUrl) {
        try {
            InputStream inputStream = part.getInputStream();
            FileOutputStream outputStream = new FileOutputStream(new File(amatarUrl));
            byte[] buff=new byte[1024];
            int line=0;
            while ((line=inputStream.read(buff))!=-1){
                outputStream.write(buff,0,line);
            }
            outputStream.flush();
            inputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
