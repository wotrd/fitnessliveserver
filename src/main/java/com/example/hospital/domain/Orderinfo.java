package com.example.hospital.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Orderinfo)实体类
 *
 * @author wotrd
 * @since 2019-06-10 23:19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orderinfo implements Serializable {

    private static final long serialVersionUID = 867934553814569407L;

    //主键id
    private Long id;
    //患者编号
    private Long patientId;
    //患者姓名
    private String patientName;
    //科室名字
    private String departName;
    //医生名字
    private String doctorName;
    //预约时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    //创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}