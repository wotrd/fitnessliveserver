package com.example.libraryserver.customer.mapper;

import com.example.libraryserver.customer.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * (user)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-01 17:12:31
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserPo queryById(Long id);

    /**
     * 通过 mobileNum 查询单条数据
     *
     * @param mobileNum 手机号
     * @return 实例对象
     */
    UserPo getByMobileNum(@Param("mobileNum") String mobileNum);

    /**
     *
     * @param userPo
     * @return
     */
    int updateByAccount(UserPo userPo);

    /**
     * 通过 account 查询单条数据
     *
     * @param account 用户账户
     * @return 对象列表
     */
    UserPo getUserByAccount(@Param("account") String account);

    /**
     * 模糊查询
     *
     * @param searchText
     * @return
     */
    List<UserPo> getUserLikeAccount(@Param("searchText") String searchText);

    /**
     * 通过账户或者昵称模糊查询
     *
     * @param useraccount
     * @param nickname
     * @return
     */
    List<UserPo> getUserLikeAccountOrNickName(@Param("useraccount") String useraccount, @Param("nickname") String nickname);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键数组
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") String[] ids);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<UserPo> queryAll(UserPo user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(UserPo user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(UserPo user);


}