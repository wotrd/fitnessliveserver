package com.example.wotrd.fitnessliveserver.manager.domain;

/**
 * Created by wkj_pc on 2017/8/22.
 */
public class LiveTheme {
    private Integer ltid;
    private String lttheme;
    private Integer islive;
    private Integer uid;

    public Integer getIslive() {
        return islive;
    }

    public void setIslive(Integer islive) {
        this.islive = islive;
    }

    public Integer getLtid() {
        return ltid;
    }

    public void setLtid(Integer ltid) {
        this.ltid = ltid;
    }

    public String getLttheme() {
        return lttheme;
    }

    public void setLttheme(String lttheme) {
        this.lttheme = lttheme;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
