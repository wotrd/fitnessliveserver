package com.example.wotrd.fitnessliveserver.manager.domain;

/**
 * Created by wkj_pc on 2017/8/20.
 */
public class Attention {
    private Integer gzid;
    private String gzaccount;
    private String gznickname;
    private String gzphonenumber;
    private String gzamatar;
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

    public String getGzaccount() {
        return gzaccount;
    }

    public void setGzaccount(String gzaccount) {
        this.gzaccount = gzaccount;
    }

    public String getGznickname() {
        return gznickname;
    }

    public void setGznickname(String gznickname) {
        this.gznickname = gznickname;
    }

    public String getGzphonenumber() {
        return gzphonenumber;
    }

    public void setGzphonenumber(String gzphonenumber) {
        this.gzphonenumber = gzphonenumber;
    }

    public String getGzamatar() {
        return gzamatar;
    }

    public void setGzamatar(String gzamatar) {
        this.gzamatar = gzamatar;
    }

    public Integer getGzid() {

        return gzid;
    }

    public void setGzid(Integer gzid) {
        this.gzid = gzid;
    }
}
