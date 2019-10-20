package com.example.hospital.mapper;

import com.example.hospital.domain.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Patient)表数据库访问层
 *
 * @author wotrd
 * @since 2019-06-10 13:29:58
 */
@Repository
@Mapper
public interface PatientMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Patient queryById(Long id);

    Patient queryByAccount(String account);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Patient> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param patient 实例对象
     * @return 对象列表
     */
    List<Patient> queryAll(Patient patient);

    /**
     * 新增数据
     *
     * @param patient 实例对象
     * @return 影响行数
     */
    int insert(Patient patient);

    /**
     * 修改数据
     *
     * @param patient 实例对象
     * @return 影响行数
     */
    int update(Patient patient);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);


}