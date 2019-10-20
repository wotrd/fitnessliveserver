package com.example.hospital.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Caseinfo)实体类
 *
 * @author wotrd
 * @since 2019-06-11 13:34:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Caseinfo implements Serializable {
    private static final long serialVersionUID = 431422057717769269L;
    //主键id
    private Long id;
    //预约人
    private String orderUser;
    //医生名字
    private String doctorName;
    //科室名字
    private String departName;
    //病情诊
    private String bingQingZhen;
    //处方
    private String chuFang;
    //处理时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dealTime;


}