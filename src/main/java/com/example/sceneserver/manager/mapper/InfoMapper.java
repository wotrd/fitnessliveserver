package com.example.sceneserver.manager.mapper;

import com.example.sceneserver.manager.po.AppraisePo;
import com.example.sceneserver.manager.po.InfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InfoDO record);

    int insertSelective(InfoDO record);

    InfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InfoDO record);

    int updateByPrimaryKey(InfoDO record);

    List<InfoDO> queryByName(String title);

    List<InfoDO> queryLikeName(String name);

    int deleteByIds(@Param("ids") String[] ids);
}