package com.example.animalsalesserver.manager.po;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表(Businesses)实体类
 *
 * @author wotrd
 * @since 2019-10-04 14:47:10
 */
@Builder
@Data
public class BusinessPo implements Serializable {
    private static final long serialVersionUID = -44098401377051761L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品名字
     */
    private String bName;
    /**
     * 商品类型
     */
    private String type;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品图片
     */
    private String avatar;



}