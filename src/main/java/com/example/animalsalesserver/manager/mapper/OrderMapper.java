package com.example.animalsalesserver.manager.mapper;

import com.example.animalsalesserver.manager.po.OrderPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Orders)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-04 15:54:33
 */
@Repository
@Mapper
public interface OrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderPo queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param bName
     * @param buyerName
     * @param sellName
     * @return 对象列表
     */
    List<OrderPo> queryLikeNameAndBuyerAndSell(@Param("bName") String bName, @Param("buyerName") String buyerName, @Param("sellName") String sellName);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param buyerId 买家ID
     * @return 对象列表
     */
    List<OrderPo> queryAll(Long buyerId);

    /**
     * 新增数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int insert(OrderPo orders);

    /**
     * 修改数据
     *
     * @param orders 实例对象
     * @return 影响行数
     */
    int update(OrderPo orders);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过主键数组删除数据
     *
     * @param ids 主键
     * @return 影响行数
     */
    int deleteByIds(@Param("ids") String[] ids);

}