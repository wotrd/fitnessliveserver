package com.example.animalsalesserver.manager.mapper;

import com.example.animalsalesserver.manager.po.AppraisePo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评价表(Appraise)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-15 22:42:11
 */
@Repository
@Mapper
public interface AppraiseMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AppraisePo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AppraisePo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param appraise 实例对象
     * @return 对象列表
     */
    List<AppraisePo> queryAll(AppraisePo appraise);

    /**
     * 新增数据
     *
     * @param appraise 实例对象
     * @return 影响行数
     */
    int insert(AppraisePo appraise);

    /**
     * 修改数据
     *
     * @param appraise 实例对象
     * @return 影响行数
     */
    int update(AppraisePo appraise);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 批量删除
     *
     * @param split
     * @return
     */
    int deleteByIds(@Param("ids") String[] split);

    /**
     * 根据商品名字查找
     *
     * @param name
     * @return
     */
    List<AppraisePo> queryLikeName(String name);

    List<AppraisePo> queryByName(String bName);
}