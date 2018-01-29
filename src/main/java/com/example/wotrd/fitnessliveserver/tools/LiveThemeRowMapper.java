package com.example.wotrd.fitnessliveserver.tools;

import com.example.wotrd.fitnessliveserver.manager.domain.LiveTheme;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkj_pc on 2017/8/22.
 */
@Repository
public class LiveThemeRowMapper implements RowMapper {
    /** 用户直播风格的sql映射*/
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        LiveTheme liveTheme=new LiveTheme();
        liveTheme.setLtid(rs.getInt("lt_id"));
        liveTheme.setLttheme(rs.getString("lt_name"));
        liveTheme.setUid(rs.getInt("uid"));
        liveTheme.setIslive(rs.getInt("lt_islive"));
        return liveTheme;
    }
}
