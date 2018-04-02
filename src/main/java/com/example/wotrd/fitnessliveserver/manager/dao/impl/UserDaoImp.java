package com.example.wotrd.fitnessliveserver.manager.dao.impl;

import com.example.wotrd.fitnessliveserver.manager.dao.IUserDao;
import com.example.wotrd.fitnessliveserver.manager.domain.UploadVideo;
import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.Page;
import com.example.wotrd.fitnessliveserver.tools.StringUtil;
import com.example.wotrd.fitnessliveserver.tools.UploadVideoMapper;
import com.example.wotrd.fitnessliveserver.tools.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wkj_pc on 2017/6/5.
 */
@Repository
public class UserDaoImp implements IUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private UserRowMapper userRowMapper;

    @Autowired
    private UploadVideoMapper uploadVideoMapper;

    @Override
    public User queryUserByAccountExceptUid(String account, int uid) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime " +
                "from users where account=? ";
        List query = jdbcTemplate.query(sql, new String[]{account},userRowMapper);
        if (null==query||query.size()==0){
            return null;
        }
        User user = (User) query.get(0);
        return (user.getUid()==uid)?null:user;
    }

    @Override
    public User queryUserByEmailExceptUid(String email, int uid) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime " +
                "from users where email=?";
        List query = jdbcTemplate.query(sql, new String[]{email},userRowMapper);
        if (null==query||query.size()==0){
            return null;
        }
        User user = (User) query.get(0);
        return (user.getUid()==uid)?null:user;
    }

    @Override
    public User queryUserByPhonenumExceptUid(String mobilenum, int uid) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime " +
                "from users where phonenum=?";
        List query = jdbcTemplate.query(sql, new String[]{mobilenum},userRowMapper);
        if (null==query||query.size()==0){
            return null;
        }
        User user = (User) query.get(0);
        return (user.getUid()==uid)?null:user;
    }

    @Override
    public User queryUserByIdcardExceptUid(String idcard, int uid) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime " +
                "from users where idcard=?";
        List query = jdbcTemplate.query(sql, new String[]{idcard},userRowMapper);
        if (null==query||query.size()==0){
            return null;
        }
        User user = (User) query.get(0);
        return (user.getUid()==uid)?null:user;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserByUid(User user) {
        String sql="UPDATE users SET account=?,name=?,nickname=?,borndata=?,gender=?,role=?,grade=?,personalsign=?," +
                "idcard=?,email=?,phonenum=?,password=?,amatar=?,createtime=? WHERE uid=?";
        int updateRows = jdbcTemplate.update(sql, user.getAccount(), user.getName(),user.getNickname(), user.getBorndata(),
                user.getGender(),user.getRole(),user.getGrade(),user.getPersonalsign(),user.getIdcard(),user.getEmail(),
                user.getPhonenum(),user.getPassword(), user.getAmatar(),user.getCreatetime(),user.getUid());
        System.out.println(updateRows);
        return (updateRows>0)?true:false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteByIds(String ids){
        return jdbcTemplate.update("delete from users where uid in("+ids+")");
    }
    @Override
    public Page queryUserList(Map<String, Object> params) {
            StringBuffer sql =new StringBuffer();
            sql.append("select uid,account,password,name,gender,nickname,email,idcard,phonenum,role,amatar,borndata," +
                    "personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime from users where 1=1");
            if(!StringUtil.isNull((String)params.get("useraccount"))){
                sql.append(" and account like '%").append((String)params.get("useraccount")).append("%'");
            }
            if(!StringUtil.isNull((String)params.get("usernickname"))){
                sql.append(" and nickname like '%").append((String)params.get("usernickname")).append("%'");
            }
            Page page = new Page(sql.toString(), Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()), jdbcTemplate);
            return page;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(User user) {
        String sql="insert into users (account,name,password,gender,nickname,email,idcard,phonenum,role,borndata,createtime) " +
                "values(?,?,?,?,?,?,?,?,?,?,?)";
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd:HH/mm/ss");
        int updateRows = jdbcTemplate.update(sql,
                user.getAccount(), user.getName(), user.getPassword(), user.getGender()
                        , user.getNickname(), user.getEmail(), user.getIdcard(), user.getPhonenum(),
                user.getRole(),user.getBorndata(),dateFormat.format(new Date()));
        return (updateRows>0)?true:false;
    }

    @Override
    public List<UploadVideo> getAllUploadVideos() {
        String sql="select uv_id,uv_title,uv_videourl,uv_thumbnailurl,uv_uploadtime,uid from uploadvideos";
        List query = jdbcTemplate.query(sql, uploadVideoMapper);
        return (query.size()>0)?query:null;
    }

    @Override
    public User queryUserByAccountAndPassword(String account, String password) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime " +
                "from users where account=? and password=?";
        List query = jdbcTemplate.query(sql, new String[]{account, password}, userRowMapper);
        return (query.size()>0)?(User)query.get(0):null;
    }
    @Override
    public User queryUserByEmail(String email) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime  from users where email=?";
        List<User> query=jdbcTemplate.query(sql,new String[]{email}, userRowMapper);
        return (query.size()>0)?(User)query.get(0):null;
    }

    @Override
    public User queryUserByIdcard(String idcard) {
        String sql="SELECT uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime  FROM users where idcard=?";
        List query = jdbcTemplate.query(sql, new String[]{idcard}, userRowMapper);
        return (query.size()>0)?(User)query.get(0):null;
    }

    @Override
    public User queryUserByPhonenum(String phonenum) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime  from users where phonenum=?";
        List query = jdbcTemplate.query(sql, new String[]{phonenum}, userRowMapper);
        return (query.size()>0)?(User)query.get(0):null;
    }

    @Override
    public User queryUserByAccount(String account) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign ,islive,grade,fansnum,attentionnum,livebigpic,createtime from users where account=?";
        List queryList = jdbcTemplate.query(sql, new String[]{account}, userRowMapper);
        return (queryList.size()>0)?(User)queryList.get(0):null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserByAccount(String account) {
        String sql="delete from users where account=?";
        int deleteRows = jdbcTemplate.update(sql, account);
        return (deleteRows>0)?true:false;
    }

    @Override
    public User queryUserById(int id) {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,islive,grade,fansnum,attentionnum,livebigpic,createtime  from users where uid=?";
        List query = jdbcTemplate.query(sql,new Integer[]{id}, userRowMapper);
        return (query.size()>0)?(User)query.get(0):null;
    }

    @Override
    public List<User> queryAdminAll() {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic from Admin where role<3";
        List<User> users = jdbcTemplate.queryForList(sql, User.class);
        return users;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(User user) {
        String sql="insert into users (account,name,password,gender,nickname,borndata,email,idcard,phonenum,role) " +
                "values(?,?,?,?,?,?,?,?,?,?)";
        int updateRows = jdbcTemplate.update(sql,
                new String[]{user.getAccount(), user.getName(), user.getPassword(), user.getGender()
                , user.getNickname(),user.getBorndata(), user.getEmail(), user.getIdcard(), user.getPhonenum()},
                new int[]{3});
        return (updateRows>0)?true:false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserById(int id) {
        String sql="DELETE FROM USERS WHERE uid=?";
        int deleteRows = jdbcTemplate.update(sql, id);
        return (deleteRows>0)?true:false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(User user) {
        String sql="update users set account=?,name=?,password=?,gender=?," +
                "nickname=?,email=?,idcard=?,phonenum=?,role=?,borndata=? WHERE uid=?";
        int updateRows = jdbcTemplate.update(sql, user.getAccount(), user.getName(),
                user.getPassword(), user.getGender(), user.getNickname(), user.getEmail(),
                user.getIdcard(), user.getPhonenum(), user.getRole(),
                user.getBorndata(), user.getUid());
        return (updateRows>0)?true:false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUser() {
        int deleteRows = jdbcTemplate.update("delete * from users");
        return (deleteRows>0)?true:false;
    }

    @Override
    public List<User> queryUserAll() {
        String sql="select uid,account,name,password,gender,nickname,email,idcard,phonenum," +
                "role,amatar,borndata,personalsign,islive,grade,fansnum,attentionnum,livebigpic,createtime  from users";
        List<User> users = jdbcTemplate.queryForList(sql, User.class);
        return (users.size()>0)?users:null;
    }

}
