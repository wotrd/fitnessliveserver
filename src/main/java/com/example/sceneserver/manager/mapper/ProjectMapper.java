package com.example.sceneserver.manager.mapper;

import com.example.sceneserver.manager.po.ProjectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectDO record);

    int insertSelective(ProjectDO record);

    ProjectDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectDO record);

    int updateByPrimaryKey(ProjectDO record);

    /**
     * 通过 name 作为筛选条件查询
     *
     * @param name 查询起始位置
     * @return 对象列表
     */
    List<ProjectDO> queryLikeName(String name);

    List<ProjectDO> queryByName(String name);

    int deleteByIds(@Param("ids") String[] ids);
}