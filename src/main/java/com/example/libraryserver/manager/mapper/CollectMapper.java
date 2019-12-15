package com.example.libraryserver.manager.mapper;

import com.example.libraryserver.manager.po.CollectPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评价表(collectPo)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-15 22:42:11
 */
@Repository
@Mapper
public interface CollectMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CollectPo queryById(Long id);

    List<CollectPo> queryByUserId(Long userId);

    /**
     * 通过实体作为筛选条件查询
     *
     * @return 对象列表
     */
    List<CollectPo> queryAll();

    /**
     * 新增数据
     *
     * @param collectPo 实例对象
     * @return 影响行数
     */
    int insert(CollectPo collectPo);


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
     * 根据图书名字查找
     *
     * @param name
     * @return
     */
    List<CollectPo> queryLikeName(String name);

    List<CollectPo> queryByName(String name);


}