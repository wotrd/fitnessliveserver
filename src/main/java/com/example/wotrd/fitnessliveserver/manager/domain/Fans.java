package com.example.wotrd.fitnessliveserver.manager.domain;

/**
 * Created by wkj_pc on 2017/8/16.
 */
public class Fans {
    private Integer fid;
    private String faccount;
    private String fnickname;
    private String fphonenumber;
    private String famatar;
    private Integer uid;
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Fans() {}
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFaccount() {
        return faccount;
    }

    public void setFaccount(String faccount) {
        this.faccount = faccount;
    }

    public String getFnickname() {
        return fnickname;
    }

    public void setFnickname(String fnickname) {
        this.fnickname = fnickname;
    }

    public String getFphonenumber() {
        return fphonenumber;
    }

    public void setFphonenumber(String fphonenumber) {
        this.fphonenumber = fphonenumber;
    }

    public String getFamatar() {
        return famatar;
    }

    public void setFamatar(String famatar) {
        this.famatar = famatar;
    }
}
