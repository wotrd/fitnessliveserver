package com.example.animalsalesserver.manager.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 评价表(Appraise)实体类
 *
 * @author wotrd
 * @since 2019-10-15 22:44:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintPo implements Serializable {
    private static final long serialVersionUID = 311453241655905716L;
    
    private Long id;
    /**
     * 商品名字
     */
    private String name;
    /**
     * 商品原因
     */
    private String reason;
    /**
     * 评价时间
     */
    private Date createTime;

}