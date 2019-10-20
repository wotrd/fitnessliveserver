package com.example.hospital.service;

import com.example.hospital.domain.Caseinfo;
import com.example.hospital.mapper.CaseinfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CaseInfoService {

    @Autowired
    private CaseinfoMapper caseinfoMapper;

    /**
     * 查询病例
     *
     * @param param
     * @return
     */
    public Map<String, Object> getCaseInfo(Map<String, Object> param) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            List<Caseinfo> caseinfos = caseinfoMapper.queryAll(param.get("account").toString(),
                    null==param.get("reason")?"":param.get("reason").toString());
            returnMap.put("code", 200);
            returnMap.put("data", caseinfos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }

    public Map<String, Object> getCaseInfoByBqz(Map<String, Object> param) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Caseinfo caseinfo = caseinfoMapper.queryByBqz(param.get("bqz").toString());
            returnMap.put("code", 200);
            returnMap.put("data", caseinfo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }
}
