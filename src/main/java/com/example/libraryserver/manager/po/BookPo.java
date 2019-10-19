package com.example.libraryserver.manager.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品表(Businesses)实体类
 *
 * @author wotrd
 * @since 2019-10-04 14:47:10
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookPo implements Serializable {
    private static final long serialVersionUID = -44098401377051761L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品名字
     */
    private String name;
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
    /**
     * 卖家ID
     */
    private Long sellerId;
    /**
     * 卖家名字
     */
    private String sellerName;



}