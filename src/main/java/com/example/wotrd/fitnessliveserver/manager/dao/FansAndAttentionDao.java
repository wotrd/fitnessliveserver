package com.example.wotrd.fitnessliveserver.manager.dao;


import com.example.wotrd.fitnessliveserver.manager.domain.User;
import com.example.wotrd.fitnessliveserver.tools.Page;

import java.util.Map;

public interface FansAndAttentionDao {

    Page queryAttentionList(Map<String, Object> params);

    Page queryFansList(Map<String, Object> params);

    User queryUserByAccount(String account);

    boolean addFans(String useraccount, String fansaccount);

    boolean getFansByAccount(String useraccount, String fansaccount);

    boolean getAttentionsByAccount(String useraccount, String attentionaccount);

    boolean attention(String useraccount, String attentionaccount);

    boolean deleteFans(String useraccount, String fansAccount);

    boolean cancelAttention(String useraccount, String attentionsaccount);
}
