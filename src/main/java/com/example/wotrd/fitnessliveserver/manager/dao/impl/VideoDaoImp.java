package com.example.wotrd.fitnessliveserver.manager.dao.impl;

import com.example.wotrd.fitnessliveserver.manager.dao.VideoDao;
import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.StringUtil;
import com.example.wotrd.fitnessliveserver.tools.UploadVideoMapper;
import com.example.wotrd.fitnessliveserver.tools.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class VideoDaoImp implements VideoDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserRowMapper userRowMapper;
    @Autowired
    UploadVideoMapper uploadVideoMapper;

    @Transactional
    @Override
    public boolean uploadSysVideo(String title,String videoUrl,String picUrl,String uploadTime,int type,int uid) {
        String sql="INSERT INTO sysvideos(uv_title, uv_videourl," +
                " uv_thumbnailurl,uv_uploadtime,uv_type,uid) VALUES (?,?,?,?,?,?);";

        int update = jdbcTemplate.update(sql, title, videoUrl, picUrl,uploadTime, type, uid);
        return (update>0)?true:false;
    }

    /**
     * 通过id获取视频列表
     * @param ids
     */
    @Override
    public List<UploadVideo> getVideoListByIds(String ids) {
        List<UploadVideo> query = jdbcTemplate.query("select * from sysvideos where " +
                "uv_id in(" + ids + ")", uploadVideoMapper);
        return query;
    }
    /**
     * 通过id删除视频
     * @param ids
     */
    @Transactional
    @Override
    public int deleteByIds(String ids) {
        return jdbcTemplate.update("delete from sysvideos where sysvideos.uv_id in("+ids+")");
    }
    /**
     * 获取系统视频列表
     */
    @Override
    public Page querySysVideoList(Map<String, Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select * from sysvideos where 1=1");
        if(!StringUtil.isNull((String)params.get("title"))){
            sql.append(" and uv_title like '%").append((String)params.get("title")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("type"))){
            sql.append(" and uv_type like '%").append((String)params.get("type")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()),
                Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
