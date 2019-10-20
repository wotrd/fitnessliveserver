package com.example.hospital.service;

import com.example.hospital.domain.Patient;
import com.example.hospital.mapper.PatientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {

    @Autowired
    private PatientMapper patientMapper;

    /**
     * 注册用户
     * @param patient
     * @return
     */
    public Map<String, Object> register(Patient patient) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            Patient patient1 = patientMapper.queryByAccount(patient.getAccount());
            if (null == patient1){
                patientMapper.insert(patient);
                returnMap.put("code", 200);
                returnMap.put("data", patient);
            }else {
                returnMap.put("code", 201);
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }

    /**
     * 用户登录
     * @param patient
     * @return
     */
    public Map<String, Object> login(Patient patient) {

        Map<String, Object> returnMap = new HashMap<>();

        try {
            patient = patientMapper.queryByAccount(patient.getAccount());
            if (null != patient){
                patient.setIsLogin(1);
                patientMapper.update(patient);
                returnMap.put("code", 200);
                returnMap.put("data", patient);
            }else {
                returnMap.put("code", 201);
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }

    /**
     * 退出登录
     * @param patient
     * @return
     */
    public Map<String, Object> quitLogin(Patient patient) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            patient.setIsLogin(0);
            patientMapper.update(patient);
            returnMap.put("code", 200);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }

    public Map<String, Object> bindInfo(Patient patient) {
        Map<String, Object> returnMap = new HashMap<>();
        try {
            patientMapper.update(patient);
            returnMap.put("code", 200);
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }

        return returnMap;
    }
}
