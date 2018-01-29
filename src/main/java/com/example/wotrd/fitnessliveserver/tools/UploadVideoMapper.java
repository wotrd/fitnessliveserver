package com.example.wotrd.fitnessliveserver.tools;

import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkj on 2017/9/12.
 */
@Repository
public class UploadVideoMapper  implements RowMapper {
    /** 用户直播风格的sql映射*/
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        UploadVideo video=new UploadVideo();
        video.setUid(rs.getInt("uv_id"));
        video.setThumbnailurl(rs.getString("uv_thumbnailurl"));
        video.setTitle(rs.getString("uv_title"));
        video.setUploadtime(rs.getString("uv_uploadtime"));
        video.setVideourl(rs.getString("uv_videourl"));
        video.setVid(rs.getInt("uv_id"));
        video.setType(rs.getInt("uv_type"));
        return video;
    }
}