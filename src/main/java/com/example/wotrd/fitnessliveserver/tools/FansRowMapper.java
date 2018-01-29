package com.example.wotrd.fitnessliveserver.tools;

import com.example.wotrd.fitnessliveserver.manager.domain.Fans;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wkj_pc on 2017/8/16.
 */
@Repository
public class FansRowMapper implements RowMapper {
    /**fans表的 sql的行映射*/
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fans fans=new Fans();
        fans.setFid(rs.getInt("fs_id"));
        fans.setFaccount(rs.getString("fs_account"));
        fans.setFnickname(rs.getString("fs_nickname"));
        fans.setFphonenumber(rs.getString("fs_phonenum"));
        fans.setFamatar(rs.getString("fs_amatar"));
        fans.setUid(rs.getInt("uid"));
        fans.setAccount(rs.getString("account"));
        return fans;
    }
}
