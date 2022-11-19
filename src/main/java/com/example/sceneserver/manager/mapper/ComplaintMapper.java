package com.example.sceneserver.manager.mapper;

import com.example.sceneserver.manager.po.ComplaintPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 举报表(Complaint)表数据库访问层
 *
 * @author wotrd
 * @since 2019-10-15 22:42:11
 */
@Repository
@Mapper
public interface ComplaintMapper {

    /**
     * 新增数据
     *
     * @param complaintPo 实例对象
     * @return 影响行数
     */
    int insert(ComplaintPo complaintPo);


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
    List<ComplaintPo> queryLikeName(String name);


}