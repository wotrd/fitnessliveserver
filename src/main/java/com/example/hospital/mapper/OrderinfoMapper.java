package com.example.hospital.mapper;

import com.example.hospital.domain.Orderinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Orderinfo)表数据库访问层
 *
 * @author wotrd
 * @since 2019-06-10 23:19:31
 */
@Repository
@Mapper
public interface OrderinfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Orderinfo queryById(Long id);

    List<Orderinfo> queryByAccount(String account);
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Orderinfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param orderinfo 实例对象
     * @return 对象列表
     */
    List<Orderinfo> queryAll(Orderinfo orderinfo);

    /**
     * 新增数据
     *
     * @param orderinfo 实例对象
     * @return 影响行数
     */
    int insert(Orderinfo orderinfo);

    /**
     * 修改数据
     *
     * @param orderinfo 实例对象
     * @return 影响行数
     */
    int update(Orderinfo orderinfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


}