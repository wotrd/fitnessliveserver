package com.example.wotrd.fitnessliveserver.manager.service;


import com.example.wotrd.fitnessliveserver.manager.dao.LiveUserDao;
import com.example.wotrd.fitnessliveserver.manager.domain.LearnResource;
import com.example.wotrd.fitnessliveserver.tools.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */
@Service
public class LiveService {

    @Autowired
    LiveUserDao learnDao;

    public int add(LearnResource learnResouce) {
        return this.learnDao.add(learnResouce);
    }


    public int update(LearnResource learnResouce) {
        return this.learnDao.update(learnResouce);
    }


    public int deleteByIds(String ids) {
        return this.learnDao.deleteByIds(ids);
    }


    public LearnResource queryLearnResouceById(Long id) {
        return this.learnDao.queryLearnResouceById(id);
    }


    public Page queryLearnResouceList(Map<String,Object> params) {
        return this.learnDao.queryLearnResouceList(params);
    }
}
