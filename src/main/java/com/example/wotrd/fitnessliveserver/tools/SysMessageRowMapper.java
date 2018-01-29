package com.example.wotrd.fitnessliveserver.tools;

import com.example.wotrd.fitnessliveserver.manager.domain.SysMessage;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SysMessageRowMapper implements RowMapper {
    /**
     * sql的行映射
     */
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        SysMessage message=new SysMessage();
        message.setSmid(rs.getInt("sm_id"));
        message.setTitle(rs.getString("sm_title"));
        message.setContent(rs.getString("sm_content"));
        message.setFrom(rs.getString("sm_from"));
        message.setTo(rs.getString("sm_to"));
        message.setIntent(rs.getInt("intent"));
        message.setIsRead(rs.getInt("isRead"));
        message.setOwner(rs.getString("owner"));
        message.setTime(rs.getNString("time"));
        message.setResult(rs.getString("result"));
        message.setUid(rs.getInt("uid"));
        return message;
    }
}
