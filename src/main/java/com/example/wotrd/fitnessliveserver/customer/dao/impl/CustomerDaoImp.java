package com.example.wotrd.fitnessliveserver.customer.dao.impl;

import com.example.wotrd.fitnessliveserver.customer.dao.ICustomerDao;
import com.example.wotrd.fitnessliveserver.manager.domain.*;
import com.example.wotrd.fitnessliveserver.tools.*;
import com.mysql.jdbc.log.LogUtils;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wkj_pc on 2017/6/17.
 */
@Repository
public class CustomerDaoImp implements ICustomerDao {

    @Autowired
    private JdbcTemplate template;
    @Autowired
    private UserRowMapper userRowMapper;
    @Autowired
    private LiveThemeRowMapper liveThemeMapper;
    @Autowired
    private UploadVideoMapper uploadVideoMapper;
    @Autowired
    private AttentionRowMapper attentionRowMapper;

    private Category logger = Logger.getInstance("CustomerDaoImp");
    private JdbcTemplate wbTemplate = new JdbcTemplate(DataSourceTools.getDataSource());
    private FansRowMapper fansRowMapper = new FansRowMapper();
    private UserRowMapper wbUserRowMapper = new UserRowMapper();

    /**
     * 获取系统视频
     */
    @Override
    public List<UploadVideo> getSysVideos() {
        String getVideoSql = "SELECT uv_id,uv_thumbnailurl,uv_title,uv_type,uv_uploadtime," +
                "uv_videourl FROM sysvideos WHERE uv_type=0;";
        List query = template.query(getVideoSql, uploadVideoMapper);
        return (null != query) ? query : null;
    }

    /**
     * SELECT uid,account,name,password,gender,nickname,email,
     * idcard,phonenum,role,amatar,age,personalsign,islive,grade,
     * fansnum,attentionnum,livebigpic,createtime FROM users
     */
    @Override
    public List<User> customerSearchUser(String searchText, String account) {

        String sql1 = "SELECT * FROM users WHERE role=0 AND (nickname LIKE ? OR account LIKE ?) AND account != ?;";
        List<User> query = template.query(sql1, new String[]{'%' + searchText + '%', '%' + searchText + '%', account},
                userRowMapper);
        return query;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addSysMessage(SysMessage message) {
        String addMsg = "INSERT INTO sysmessage(sm_title, sm_content," +
                " sm_from, sm_to, intent, isRead, owner, time, result, uid)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?);";
        int update = template.update(addMsg, message.getTitle(), message.getContent(), message.getFrom(),
                message.getTo(), message.getIntent(), message.getIsRead(), message.getOwner(), message.getTime(),
                message.getResult(), message.getUid());
        return update > 0 ? true : false;
    }

    @Override
    public List<Attention> getAttentionUserInfoByIdByAccount(String account) {
        User user = getLiveUserInfoByAccount(account);
        String getAttentionsSql = "SELECT gz_id,gz_account,gz_nickname," +
                "gz_phonenum,gz_amatar,uid,account FROM attentions where uid=?;";
        List query = template.query(getAttentionsSql, new Integer[]{user.getUid()}, attentionRowMapper);
        return query.size() > 0 ? query : null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean setUserIsAttention(Attention attention, String type) {
        if (type.equals("canceled")) {
            //将用户一关注数量减一，将关注表中的信息删除。用户而粉丝数减一，粉丝表信息删
            String divAttentionSql = "update users set attentionnum =attentionnum-1 where uid=?";
            String divFanSql = "update users set fansnum =fansnum-1 where account=?";
            String delAttentionSql = "delete from attentions where uid=? AND gz_account=?";
            String delFanSql = "delete from fans where uid=? AND fs_account=?";
            String delGrade = "update users set grade=grade-5 where account=?";
            //将用户一的关注数量减一
            int update = template.update(divAttentionSql, attention.getUid());
            //将用户二的粉丝数量减一
            int update1 = template.update(divFanSql, attention.getGzaccount());
            //将用户一的关注记录去掉
            int update2 = template.update(delAttentionSql, attention.getUid(), attention.getGzaccount());
            //获取用户一的account
            String getUser1 = "select * from users where uid=?";
            List<User> query = template.query(getUser1, new Integer[]{attention.getUid()}, userRowMapper);
            User user1;
            User user2;
            String getUser2 = "select * from users where account=?";
            List<User> query1 = template.query(getUser2, new String[]{attention.getGzaccount()}, userRowMapper);
            if (null != query && query.size() > 0 && null != query1 && query1.size() > 0) {
                //获取用户二的id
                user1 = query.get(0);
                user2 = query1.get(0);
                //将用户二的粉丝记录去掉
                int update3 = template.update(delFanSql, user2.getUid(), user1.getAccount());
                int update4 = template.update(delGrade, attention.getGzaccount());
                return (update > 0 && update1 > 0 && update2 > 0 && update3 > 0 && update4 > 0) ? true : false;
            }
            return false;
        } else {
            //将用户一关注数量加一，将关注表中的信息。用户而粉丝数加一，粉丝表信息
            String addAttentionSql = "update users set attentionnum = attentionnum+1 where uid=?";
            String addFanSql = "update users set fansnum =fansnum+1 where uid=?";
            String insertFanSql = "insert into fans(fs_account,fs_nickname,fs_phonenum,fs_amatar,uid,account) VALUE(?,?,?,?,?,?)";
            String insertAttentionSql = "insert into attentions(gz_account, gz_nickname, gz_phonenum, gz_amatar, uid,account) VALUE(?,?,?,?,?,?)";
            String addGrade = "update users set grade=grade+5 where account=?";
            int update = template.update(addAttentionSql, attention.getUid());//将用户一的关注数量加一
            //通过id获取用户一的信息
            String getUserOne = "select * from users where uid=?";
            User user1 = (User) template.query(getUserOne, new Integer[]{attention.getUid()}, userRowMapper).get(0);
            //通过account获取用户二的信息
            String getUidSql = "select * from users where account=?";
            User user2 = (User) template.query(getUidSql, new String[]{attention.getGzaccount()}, userRowMapper).get(0);
            //将用户二的粉丝数量加一
            int update1 = template.update(addFanSql, user2.getUid());
            //插入用户一的关注记录
            int update3 = template.update(insertAttentionSql, attention.getGzaccount(), attention.getGznickname(), attention.getGzphonenumber(), attention.getGzamatar(), user1.getUid(), user1.getAccount());
            //插入用户二的粉丝记录
            int update2 = template.update(insertFanSql, user1.getAccount(), user1.getNickname(), user1.getPhonenum(), user1.getAmatar(), user2.getUid(), user2.getAccount());
            int update4 = template.update(addGrade, user2.getAccount());
            return (update > 0 && update1 > 0 && update2 > 0 && update3 > 0 && update4 > 0) ? true : false;
        }
    }

    @Override
    public List<Fans> getFansUserInfoById(int uid) {
        String sql = "select * from fans where uid=?";
        List<Fans> query = template.query(sql, new Integer[]{uid}, fansRowMapper);
        return (query.size() > 0) ? query : null;
    }

    @Override
    public List<Attention> getAttentionUserInfoById(int uid) {
        String sql = "select * from attentions where uid=?";
        List<Attention> query = template.query(sql, new Integer[]{uid}, attentionRowMapper);
        return (query.size() > 0) ? query : null;
    }

    @Override
    public List<User> wsGetWatcherInfoByAccount(String account) {
        String sql = "SELECT * FROM users WHERE account=?";
        List<User> query = wbTemplate.query(sql, new String[]{account}, wbUserRowMapper);
        return (query.size() > 0) ? query : null;
    }

    @Override
    public String wsGetLiveUserAmatarByAccount(String account) {
        String sql = "SELECT * FROM users WHERE account=?";
        List<User> query = wbTemplate.query(sql, new String[]{account}, wbUserRowMapper);
        return (query.size() > 0) ? query.get(0).getAmatar() : "";
    }

    @Override
    public User getLiveUserInfoByAccount(String account) {
        String sql = "select * from users where account=?";
        List<User> query = template.query(sql, new String[]{account}, userRowMapper);
        return (query.size() > 0) ? query.get(0) : null;
    }

    @Override
    public boolean setUserLiveStatusTagByAccount(int status, String account) {
        String sql = "update users set islive=? where account=?";
        return (wbTemplate.update(sql, status, account) > 0) ? true : false;
    }

    @Override
    public List<UploadVideo> getUserUploadVideoByUid(int uid) {
        String sql = "select uv_id,uv_title,uv_videourl,uv_thumbnailurl,uv_uploadtime,uv_type,uid from sysvideos " +
                "where uid=?";
        List<UploadVideo> query = template.query(sql, new Integer[]{uid}, uploadVideoMapper);
        return (query.size() > 0) ? query : null;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean uploadUserVideo(String title, String videourl, String thumbnail, int uid) {
        String sql = "insert into sysvideos(uv_title,uv_videourl,uv_thumbnailurl,uv_uploadtime,uv_type,uid) " +
                "values(?,?,?,?,?,?)";
        int update = template.update(sql, title, videourl, thumbnail, setDbTime(), 1, uid);
        return (update > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserLiveThemes(int uid, List<String> liveThemes) {
        String delUserLiveTheme = "delete from livethemes where uid=?";
        template.update(delUserLiveTheme, uid);
        try {
            BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, liveThemes.get(i));
                    ps.setBoolean(2, false);
                    ps.setInt(3, uid);
                }
                @Override
                public int getBatchSize() {
                    return liveThemes.size();
                }
            };
            String sql = "INSERT INTO livethemes(lt_name,lt_islive,uid) values(?,?,?)";
            template.batchUpdate(sql, setter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserSexByAccount(String account, String content) {
        String sql = "update users set gender=? where account=?";
        int update = template.update(sql, content, account);
        return (update > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserLiveBigPicByAccount(String account, String getImageUrl) {
        String sql = "update users set livebigpic=? where account=?";
        int update = template.update(sql, getImageUrl, account);
        return (update > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserAmatarByAccount(String account, String amatarUrl) {
        String sql = "update users set amatar=? where account=?";
        int update = template.update(sql, amatarUrl, account);
        return (update > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserNicknameByAccount(String account, String content) {
        String sql = "update users set nickname=? where account=?";
        int update = template.update(sql, content, account);
        return (update > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUserPersonalSignByAccount(String account, String content) {
        String sql = "update users set personalsign=? where account=?";
        int update = template.update(sql, content, account);
        return (update > 0) ? true : false;
    }
    @Override
    public List<LiveTheme> getAllLiveThemes() {
        String sql = "select * from livethemes where lt_islive=0";
        List query = template.query(sql, liveThemeMapper);
        return (query.size() > 0) ? query : null;

    }
    @Override
    public List<User> getAllLiveUserInfos() {
        String sql = "select * from users where islive=1";
        List<User> query = template.query(sql, userRowMapper);
        return (query.size() > 0) ? query : null;
    }
    @Override
    public int getFansNumberByAccount(String account) {
        String sql = "SELECT * FROM users WHERE account=?";
        List<User> query = wbTemplate.query(sql, new String[]{account}, wbUserRowMapper);
        return (query.size() > 0) ? query.get(0).getFansnum() : 0;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean registerWeiboUser(User loginUser) {
        String sql = "insert into users (account,name,gender,nickname,phonenum,borndata,amatar,createtime) values(?,?,?,?,?,?,?,?)";
        template.update(sql, loginUser.getName(), loginUser.getName(),
                loginUser.getGender(),
                loginUser.getNickname(), loginUser.getPhonenum(), loginUser.getBorndata(), loginUser.getAmatar(), setDbTime());
        String sql1 = "UPDATE users SET account=? WHERE account=?";
        int updateRows = template.update(sql1, getAccount(), loginUser.getName());
        return (updateRows > 0) ? true : false;
    }

    private String setDbTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH/mm/ss");
        return dateFormat.format(new Date());
    }

    @Override
    public User getUserInfoByAccount(String account) {
        String sql = "select * from users where account=?";
        List query = template.query(sql, new String[]{account}, userRowMapper);
        return (query.size() > 0) ? ((User) query.get(0)) : null;
    }

    @Override
    public User getUserInfoByName(String name) {
        String sql = "select * from users where name=?";
        List query = template.query(sql, new String[]{name}, userRowMapper);
        return (query.size() > 0) ? ((User) query.get(0)) : null;
    }

    @Override
    public User getUserInfoByMobile(String phonenum) {
        String sql = "select * from users where phonenum=?";
        List query = template.query(sql, new String[]{phonenum}, userRowMapper);
        return (query.size() > 0) ? ((User) query.get(0)) : null;
    }

    @Override
    public boolean getUserByAccountAndPwd(User loginUser) {
        String sql = "select * from users where account=? and password=?";
        List query = template.query(sql, new String[]{loginUser.getAccount(), loginUser.getPassword()}, userRowMapper);
        return (query.size() > 0) ? true : false;
    }

    @Override
    public boolean getUserByMobileAndPwd(User loginUser) {
        String sql = "select * from users where phonenum=? and password=?";
        List query = template.query(sql, new String[]{loginUser.getPhonenum(), loginUser.getPassword()}, userRowMapper);
        return (query.size() > 0) ? true : false;

    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean registerQQUser(User loginUser) {
        /* user.setAccount(openid); user.setToken("qq:"+token);user.setNickname
        user.setGender user.setAmatar*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH/mm/ss");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        String sql = "insert into users (account,name,gender,nickname,amatar,borndata,createtime) values(?,?,?,?,?,?,?)";
        template.update(sql, loginUser.getAccount(), loginUser.getAccount(), loginUser.getGender(),
                loginUser.getNickname(), loginUser.getAmatar(), dateFormat1.format(new Date()), dateFormat.format(new Date()));
        String sql1 = "UPDATE users SET account=? WHERE account=?";
        int updateRows = template.update(sql1, getAccount(), loginUser.getAccount());
        return (updateRows > 0) ? true : false;
    }

    @Override
    public boolean checkUserByNameExist(String name) {
        String sql = "select * from users where name=?";
        List query = template.query(sql, new String[]{name}, userRowMapper);
        return (query.size() > 0) ? true : false;
    }

    @Override
    public boolean checkUserExistByMobileNum(String phonenum) {
        String sql = "select * from users where phonenum=?";
        List query = template.query(sql, new String[]{phonenum}, userRowMapper);
        return (query.size() > 0) ? true : false;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateUserPassword(String mobilenum, String password) {
        String sql = "UPDATE users SET password=? where phonenum=?";
        int update = template.update(sql, new String[]{password, mobilenum});
        return (update > 0) ? "true" : "false";
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String registerUser(String mobilenum, String password) {
        String sql = "insert into users (account,password,nickname,phonenum,borndata,createtime) " +
                "values(?,?,?,?,?,?)";
        String sql1 = "UPDATE users SET account=? WHERE account=?";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH/mm/ss");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        template.update(sql, mobilenum, password, "小灰灰",
                mobilenum, dateFormat1.format(new Date()), dateFormat.format(new Date()));
        int updateRows = template.update(sql1, getAccount(), mobilenum);
        return (updateRows > 0) ? ":true" : ":false";
    }

    public String getAccount() {
        String sql = "select * from users ORDER BY uid DESC limit 1";
        List<User> query = template.query(sql, userRowMapper);
        User user = query.get(0);
        long num = user.getUid() + 100000;
        return String.valueOf(num);
    }
}
