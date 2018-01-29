package com.example.wotrd.fitnessliveserver.manager.dao;

import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.tools.Page;

import java.util.List;
import java.util.Map;

public interface VideoDao {
    Page querySysVideoList(Map<String, Object> params);

    int deleteByIds(String ids);

    List<UploadVideo> getVideoListByIds(String ids);

    boolean uploadSysVideo(String title, String videoUrl, String picUrl,String uploadTime, int type, int uid);
}
