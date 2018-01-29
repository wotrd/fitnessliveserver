package com.example.wotrd.fitnessliveserver.manager.dao.impl;

import com.example.wotrd.fitnessliveserver.manager.dao.SysMsgDao;
import com.example.wotrd.fitnessliveserver.manager.domain.SysMessage;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.StringUtil;
import com.example.wotrd.fitnessliveserver.tools.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SysMsgDaoImp implements SysMsgDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    UserRowMapper userMapper;
    @Override
    public boolean getUserByAccount(String account) {
        String sql="select * from users where account=?";
        List<User> query = jdbcTemplate.query(sql, new String[]{account}, userMapper);
        if (null==query||query.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean add(SysMessage msg) {
        String sql="insert into sysmessage (sm_title,sm_content,sm_from,sm_to,intent,owner,time,result,uid) " +
                "values(?,?,?,?,?,?,?,?,?)";
        int updateRows = jdbcTemplate.update(sql, msg.getTitle(),msg.getContent(),msg.getFrom(),msg.getTo(),
                0,msg.getTo(),msg.getTime(),msg.getResult(),msg.getUid());
        return (updateRows>0)?true:false;
    }

    @Override
    public boolean updateMsgByUid(SysMessage msg) {
        String sql="UPDATE sysmessage SET sm_title=?,sm_content=?,sm_from=?,sm_to=?,owner=?," +
                "time=?,result=? WHERE sm_id=?";
        int updateRows = jdbcTemplate.update(sql, msg.getTitle(), msg.getContent(),msg.getFrom(),
                msg.getTo(),msg.getTo(),msg.getTime()
                ,msg.getResult(), msg.getSmid());
        return (updateRows>0)?true:false;
    }

    @Override
    public int deleteByIds(String ids) {
        return jdbcTemplate.update("delete from sysmessage where sm_id in("+ids+")");
    }

    @Override
    public Page queryMsgList(Map<String, Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select sm_id,sm_title,sm_content,sm_from,sm_to,intent,isRead,owner,time,result,uid from sysmessage" +
                " where 1=1");
        if(!StringUtil.isNull((String)params.get("title"))){
            sql.append(" and sm_title like '%").append((String)params.get("title")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("to"))){
            sql.append(" and sm_to like '%").append((String)params.get("to")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}
