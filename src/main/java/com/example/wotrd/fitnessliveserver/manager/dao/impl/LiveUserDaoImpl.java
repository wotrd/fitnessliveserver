package com.example.wotrd.fitnessliveserver.manager.dao.impl;


import com.example.wotrd.fitnessliveserver.manager.dao.LiveUserDao;
import com.example.wotrd.fitnessliveserver.manager.domain.LearnResource;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tengj on 2017/4/8.
 */
@Repository
public class LiveUserDaoImpl implements LiveUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(LearnResource learnResouce) {
        return jdbcTemplate.update("insert into learn_resource(author, title,url) values(?, ?, ?)",learnResouce.getAuthor(),learnResouce.getTitle(),learnResouce.getUrl());
    }

    @Override
    public int update(LearnResource learnResouce) {
        return jdbcTemplate.update("UPDATE learn_resource SET author=?,title=?,url=? ," +
                "WHERE id=?",new Object[]{learnResouce.getAuthor(),learnResouce.getTitle(),
                learnResouce.getUrl(),learnResouce.getId()});
    }

    @Override
    public int deleteByIds(String ids){
        return jdbcTemplate.update("delete from learn_resource where id in("+ids+")");
    }

    @Override
    public LearnResource queryLearnResouceById(Long id) {
        List<LearnResource> list = jdbcTemplate.query
                ("select * from learn_resource where id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper(LearnResource.class));
        if(null != list && list.size()>0){
            LearnResource learnResouce = list.get(0);
            return learnResouce;
        }else{
            return null;
        }
    }

    @Override
    public Page queryLiveUserList(Map<String,Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select * from users where islive=0");
        if(!StringUtil.isNull((String)params.get("account"))){
            sql.append(" and account like '%").append((String)params.get("account")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("nickname"))){
            sql.append(" and nickname like '%").append((String)params.get("nickname")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
