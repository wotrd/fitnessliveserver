package com.example.wotrd.fitnessliveserver.manager.dao;


import com.example.wotrd.fitnessliveserver.tools.Page;
import java.util.Map;

/**
 * Created by tengj on 2017/4/8.
 */
public interface LiveUserDao {
    Page queryLiveUserList(Map<String, Object> params);
}
