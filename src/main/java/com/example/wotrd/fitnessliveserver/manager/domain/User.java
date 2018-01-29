package com.example.wotrd.fitnessliveserver.manager.domain;

import java.util.Objects;

/**
 * Created by wkj_pc on 2017/5/20.
 */
public class User {
    private Integer uid;
    private String account;
    private String name;
    private String password;
    private String gender;
    private String nickname;//昵称
    private String email;
    private String idcard;
    private String phonenum;
    private Integer role;       //1:main admin 2:personal admin 3: user;
    private String amatar;
    private Boolean islive;
    private String borndata;
    private String personalsign;
    private Integer fansnum;//粉丝数量
    private Integer grade;  //用户积分
    private Integer attentionnum;   //我的关注
    private String livebigpic;  //直播大图
    private String createtime;
    private String token;
    public User(){}

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getPersonalsign() {
        return personalsign;
    }

    public void setPersonalsign(String personalsign) {
        this.personalsign = personalsign;
    }

    public Integer getAttentionnum() {
        return attentionnum;
    }

    public void setAttentionnum(Integer attentionnum) {
        this.attentionnum = attentionnum;
    }

    public Integer getFansnum() {
        return fansnum;
    }

    public void setFansnum(Integer fansnum) {
        this.fansnum = fansnum;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getLivebigpic() {
        return livebigpic;
    }

    public void setLivebigpic(String livebigpic) {
        this.livebigpic = livebigpic;
    }

    public Boolean getIslive() {
        return islive;
    }

    public void setIslive(Boolean islive) {
        this.islive = islive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(account);
    }

    @Override
    public boolean equals(Object o) {
        if (o==this) {
            return true;
        }
        if (!(o instanceof User))
        {
            return false;
        }
        User user=(User)o;
        return Objects.equals(account,user.account);
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAmatar() {
        return amatar;
    }

    public void setAmatar(String amatar) {
        this.amatar = amatar;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBorndata() {
        return borndata;
    }
    public void setBorndata(String borndata){
        this.borndata=borndata;
    }

    public User(Integer uid, String account, String name, String password, String gender, String email, String borndata) {
        this.uid = uid;
        this.account = account;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.borndata = borndata;
    }
}
