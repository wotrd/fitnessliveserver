package com.example.wotrd.fitnessliveserver.manager.dao.impl;

import com.example.wotrd.fitnessliveserver.manager.dao.FansAndAttentionDao;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class FansAndAttentionDaoImp implements FansAndAttentionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;
    @Autowired
    private FansRowMapper fansRowMapper;
    @Autowired
    private AttentionRowMapper attentionRowMapper;

    /**
     * 取消关注
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean cancelAttention(String useraccount, String attentionsaccount) {
        //用户1的关注删掉
        String user1DelAttentionSql="delete from attentions where account=? and gz_account=?";
        int update1 = jdbcTemplate.update(user1DelAttentionSql,useraccount,attentionsaccount);
        //用户2的粉丝删掉
        String user1DelFanssql="delete from fans where account=? and fs_account=?";
        int update2 = jdbcTemplate.update(user1DelFanssql,attentionsaccount,useraccount);
        //用户1的关注数减一
        String subAttentionUser1Sql="update users set attentionnum=attentionnum-1 where account=?";
        int update3 = jdbcTemplate.update(subAttentionUser1Sql, useraccount);
        //用户2的粉丝数减一
        String subtrationUser2FansSql="update users set fansnum=fansnum-1 where account=?";
        int update4 = jdbcTemplate.update(subtrationUser2FansSql, attentionsaccount);
        //用户2的grade减五
        String subUser2GradeSql="update users set grade=grade-5 where account=?";
        int update5 = jdbcTemplate.update(subUser2GradeSql, attentionsaccount);
        return (update1>0)&&(update2>0)&&(update3>0)&&(update4>0)&&(update5>0)?true:false;
    }

    /**
     * 删除粉丝
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteFans(String useraccount, String fansAccount) {
        //用户1的粉丝删掉
        String user1DelFanssql="delete from fans where account=? and fs_account=?";
        int update1 = jdbcTemplate.update(user1DelFanssql,useraccount,fansAccount);
        //用户2的关注删掉
        String user2DelAttentionSql="delete from attentions where account=? and gz_account=?";
        int update2 = jdbcTemplate.update(user2DelAttentionSql,fansAccount,useraccount);
        //用户1的粉丝数减一
        String subtrationUser1FansSql="update users set fansnum=fansnum-1 where account=?";
        int update3 = jdbcTemplate.update(subtrationUser1FansSql, useraccount);
        //用户2的关注减一
        String subAttentionUser2Sql="update users set attentionnum=attentionnum-1 where account=?";
        int update4 = jdbcTemplate.update(subAttentionUser2Sql, fansAccount);
        //用户1的grade减五
        String subUser1GradeSql="update users set grade=grade-5 where account=?";
        int update5 = jdbcTemplate.update(subUser1GradeSql, useraccount);
        return (update1>0)&&(update2>0)&&(update3>0)&&(update4>0)&&(update5>0)?true:false;
    }

    /**
     * 添加用户的关注
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean attention(String useraccount, String attentionaccount) {
        String user1Sql="select * from users where account=?";
        String user2Sql="select * from users where account=?";
        List<User> users=jdbcTemplate.query(user1Sql,new String[]{useraccount},userRowMapper);
        User user1 = users.get(0);
        List<User> users1=jdbcTemplate.query(user2Sql,new String[]{attentionaccount},userRowMapper);
        User user2 = users1.get(0);
        //插入用户1的关注
        String insertUser1AttentionSql="insert into attentions(gz_account, gz_nickname, " +
                "gz_phonenum, gz_amatar, uid,account) VALUE(?,?,?,?,?,?)";
        int update1 = jdbcTemplate.update(insertUser1AttentionSql,user2.getAccount(),user2.getNickname(),
                user2.getPhonenum(),user2.getAmatar(),user1.getUid(),user1.getAccount());
        //添加用户2的粉丝
        String insertUser2FanSql="insert into fans(fs_account,fs_nickname," +
                "fs_phonenum,fs_amatar,uid,account) VALUE(?,?,?,?,?,?)";
        int update2 = jdbcTemplate.update(insertUser2FanSql,user1.getAccount(),user1.getNickname(),
                user1.getPhonenum(),user1.getAmatar(),user2.getUid(),user2.getAccount());
        //用户1的关注加一
        String addUsersAttentionSql="update users set attentionnum = attentionnum+1" +
                " where account=?";
        int update3 = jdbcTemplate.update(addUsersAttentionSql,user1.getAccount());
        //用户2的粉丝加一
        String addUsersFansSql="update users set fansnum = fansnum+1 where account=?";
        int update4 = jdbcTemplate.update(addUsersFansSql,user2.getAccount());
        //将用户2的积分加5
        String addUser2Grade="update users set grade=grade+5 where account=?";
        int update5 = jdbcTemplate.update(addUser2Grade,user2.getAccount());
        return (update1>0 && update2>0 && update3>0 && update4>0 && update5>0)?true:false;
    }
    /**
     * 添加用户的粉丝
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addFans(String useraccount, String fansaccount) {
        String user1Sql="select * from users where account=?";
        String user2Sql="select * from users where account=?";
        List<User> users=jdbcTemplate.query(user1Sql,new String[]{useraccount},userRowMapper);
        User user1 = users.get(0);
        List<User> users1=jdbcTemplate.query(user2Sql,new String[]{fansaccount},userRowMapper);
        User user2 = users1.get(0);
        //插入用户1的粉丝
        String insertUser1FanSql="insert into fans(fs_account,fs_nickname," +
                "fs_phonenum,fs_amatar,uid,account) VALUE(?,?,?,?,?,?)";
        int update1 = jdbcTemplate.update(insertUser1FanSql,user2.getAccount(),user2.getNickname(),
                user2.getPhonenum(),user2.getAmatar(),user1.getUid(),user1.getAccount());
        //添加用户2的关注
        String insertUser2AttentionSql="insert into attentions(gz_account, gz_nickname, " +
                "gz_phonenum, gz_amatar, uid,account) VALUE(?,?,?,?,?,?)";
        int update2 = jdbcTemplate.update(insertUser2AttentionSql,user1.getAccount(),user1.getNickname(),
                user1.getPhonenum(),user1.getAmatar(),user2.getUid(),user2.getAccount());
        //用户1的粉丝加一
        String addUserFansSql="update users set fansnum = fansnum+1 where account=?";
        int update3 = jdbcTemplate.update(addUserFansSql,user1.getAccount());
        //用户2的关注加一
        String addUsersAttentionsSql="update users set attentionnum = attentionnum+1" +
                " where account=?";
        int update4 = jdbcTemplate.update(addUsersAttentionsSql,user2.getAccount());
        //将用户1的积分加5
        String addUser1Grade="update users set grade=grade+5 where account=?";
        int update5 = jdbcTemplate.update(addUser1Grade,user1.getAccount());
        return (update1>0 && update2>0 && update3>0 && update4>0 && update5>0)?true:false;
    }
    /**
     * 查看用户关注是否存在
     */
    @Override
    public boolean getAttentionsByAccount(String useraccount, String attentionaccount) {
        String sql="select * from attentions where account =? and gz_account=?";
        List<User> query = jdbcTemplate.query(sql, new String[]{useraccount,
                attentionaccount}, attentionRowMapper);
        return (null!=query&&query.size()>0)?true:false;
    }
    /**
     *  获取通过账户查看粉丝是否存在
     */
    @Override
    public boolean getFansByAccount(String useraccount, String fansaccount) {
        String sql="select * from fans where account =? and fs_account=?";
        List<User> query = jdbcTemplate.query(sql, new String[]{useraccount,
                fansaccount}, fansRowMapper);
        return (null!=query&&query.size()>0)?true:false;
    }
    /**
     * 通过账户查看用户是否存在
     */
    @Override
    public User queryUserByAccount(String account) {
        String sql="select * from users where account=?";
        List<User> users=(List<User>) jdbcTemplate.query(sql,new String[]{account},userRowMapper);
        return (null!=users&& users.size()>0)?users.get(0):null;
    }
    /**
     * 获取粉丝列表
     */
    public Page queryFansList(Map<String, Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select fs_id,fs_account,fs_nickname,fs_phonenum,fs_amatar,uid,account from fans where 1=1");
        if(!StringUtil.isNull((String)params.get("account"))){
            sql.append(" and account like '%").append((String)params.get("account")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("uid"))){
            sql.append(" and uid like '%").append((String)params.get("uid")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
    /**
     * 获取关注的列表
     */
    public Page queryAttentionList(Map<String, Object> params) {
        StringBuffer sql =new StringBuffer();
        sql.append("select gz_id,gz_account,gz_nickname,gz_phonenum,gz_amatar,uid,account from attentions where 1=1");
        if(!StringUtil.isNull((String)params.get("account"))){
            sql.append(" and account like '%").append((String)params.get("account")).append("%'");
        }
        if(!StringUtil.isNull((String)params.get("uid"))){
            sql.append(" and uid like '%").append((String)params.get("uid")).append("%'");
        }
        Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
        return page;
    }
}


