package com.example.wotrd.fitnessliveserver.manager.dao.impl;


import com.example.wotrd.fitnessliveserver.manager.dao.LiveUserDao;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;
/**
 * Created by wotrd on 2017/4/8.
 */
@Repository
public class LiveUserDaoImpl implements LiveUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page queryLiveUserList(Map<String,Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select * from users where islive=1");
        if(!StringUtil.isNull((String)params.get("account"))){
            sql.append(" and account like '%").append((String)params.get("account")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("nickname"))){
            sql.append(" and nickname like '%").append((String)params.get("nickname")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()),
                Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
