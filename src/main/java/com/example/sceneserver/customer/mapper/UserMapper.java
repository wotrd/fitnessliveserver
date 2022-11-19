package com.example.sceneserver.customer.mapper;

import com.example.sceneserver.customer.po.UserDO;
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
    UserDO queryById(Long id);

    /**
     * 通过 mobileNum 查询单条数据
     *
     * @param mobileNum 手机号
     * @return 实例对象
     */
    UserDO getByMobileNum(@Param("mobileNum") String mobileNum);

    /**
     *
     * @param userDO
     * @return
     */
    int updateByAccount(UserDO userDO);

    /**
     * 通过 account 查询单条数据
     *
     * @param account 用户账户
     * @return 对象列表
     */
    UserDO getUserByAccount(@Param("account") String account);

    /**
     * 模糊查询
     *
     * @param searchText
     * @return
     */
    List<UserDO> getUserLikeAccount(@Param("searchText") String searchText);

    /**
     * 通过账户或者昵称模糊查询
     *
     * @param useraccount
     * @param areaName
     * @return
     */
    List<UserDO> getUserLikeAccountOrAreaName(@Param("useraccount") String useraccount, @Param("areaName") String areaName);

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
    List<UserDO> queryAll(UserDO user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(UserDO user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(UserDO user);


}