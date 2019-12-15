package com.example.libraryserver.manager.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
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
public class CollectPo implements Serializable {
    private static final long serialVersionUID = 311453241655905716L;
    
    private Long id;

    /**
     * 图书名字
     */
    private String name;

    /**
     * 图书类型
     */
    private String type;

    /**
     * 图书价格
     */
    private BigDecimal price;

    /**
     * 所属用户
     */
    private Long userId;

    /**
     * 评价时间
     */
    private Date createTime;
    /**
     * 评价时间
     */
    private String avatar;
}