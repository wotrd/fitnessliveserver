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
    LiveUserDao liveUserDao;

    public Page queryLiveUserList(Map<String,Object> params) {
        return this.liveUserDao.queryLiveUserList(params);
    }
}
