package com.example.wotrd.fitnessliveserver.tools;

import com.example.wotrd.fitnessliveserver.manager.domain.Attention;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkj_pc on 2017/8/20.
 */
@Repository
public class AttentionRowMapper implements RowMapper {
    /**attnetion表的 sql的行映射*/
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Attention attention=new Attention();
        attention.setGzid(rs.getInt("gz_id"));
        attention.setGzaccount(rs.getString("gz_account"));
        attention.setGznickname(rs.getString("gz_nickname"));
        attention.setGzphonenumber(rs.getString("gz_phonenum"));
        attention.setGzamatar(rs.getString("gz_amatar"));
        attention.setUid(rs.getInt("uid"));
        attention.setAccount(rs.getString("account"));
        return attention;
    }
}
