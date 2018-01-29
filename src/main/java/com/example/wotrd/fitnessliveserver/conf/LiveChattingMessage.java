package com.example.wotrd.fitnessliveserver.conf;


/**
 * Created by wkj_pc on 2017/8/7.
 */
public class LiveChattingMessage {
    private Integer mid;
    private String from;
    private String to;
    private String content;
    private String time;
    private Integer fansnumber;
    private Integer intent; //1代表聊天 2代表粉丝   3代表更新关注列表
    private Boolean  result;    //返回处理结果

    public Integer getFansnumber() {
        return fansnumber;
    }
    public void setFansnumber(Integer fansnumber) {
        this.fansnumber = fansnumber;
    }

    public Integer getIntent() {
        return intent;
    }

    public void setIntent(Integer intent) {
        this.intent = intent;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
