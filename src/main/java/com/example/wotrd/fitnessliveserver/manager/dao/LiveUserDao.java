package com.example.wotrd.fitnessliveserver.manager.dao;


import com.example.wotrd.fitnessliveserver.manager.domain.LearnResource;
import com.example.wotrd.fitnessliveserver.tools.Page;

import java.util.Map;

/**
 * Created by tengj on 2017/4/8.
 */
public interface LiveUserDao {
    int add(LearnResource learnResouce);
    int update(LearnResource learnResouce);
    int deleteByIds(String ids);
    LearnResource queryLearnResouceById(Long id);
    Page queryLearnResouceList(Map<String, Object> params);
}
