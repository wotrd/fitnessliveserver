package com.example.wotrd.fitnessliveserver.manager.dao;



import com.example.wotrd.fitnessliveserver.manager.domain.SysMessage;
import com.example.wotrd.fitnessliveserver.tools.Page;

import java.util.Map;

public interface SysMsgDao {

    Page queryMsgList(Map<String, Object> params);

    int deleteByIds(String ids);

    boolean updateMsgByUid(SysMessage msg);

    boolean add(SysMessage msg);

    boolean getUserByAccount(String to);
}
