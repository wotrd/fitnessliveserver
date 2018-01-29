package com.example.wotrd.fitnessliveserver.manager.domain;

/**
 * Created by wkj on 2017/9/12.
 */
public class UploadVideo {
    private int vid;    //视频id
    private String title;  //视频标题
    private String videourl;    //视频地址
    private String thumbnailurl;    //视频缩略图地址
    private String uploadtime;      //上传时间
    private int type;       //1代表系统视频
    private boolean isselected;
    private int uid;    //用户id
    public int getVid() {
        return vid;
    }

    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getThumbnailurl() {
        return thumbnailurl;
    }

    public void setThumbnailurl(String thumbnailurl) {
        this.thumbnailurl = thumbnailurl;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
