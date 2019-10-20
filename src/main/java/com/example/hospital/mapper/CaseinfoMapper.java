package com.example.hospital.mapper;

import com.example.hospital.domain.Caseinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Caseinfo)表数据库访问层
 *
 * @author wotrd
 * @since 2019-06-11 13:34:55
 */
@Repository
@Mapper
public interface CaseinfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Caseinfo queryById(Long id);

    Caseinfo queryByBqz(String bqz);
    /**
     * 通过实体作为筛选条件查询
     *
     * @param account 实例对象
     * @param reason
     * @return 对象列表
     */
    List<Caseinfo> queryAll(@Param("account") String account, @Param("reason") String reason);

    /**
     * 新增数据
     *
     * @param caseinfo 实例对象
     * @return 影响行数
     */
    int insert(Caseinfo caseinfo);

    /**
     * 修改数据
     *
     * @param caseinfo 实例对象
     * @return 影响行数
     */
    int update(Caseinfo caseinfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


}