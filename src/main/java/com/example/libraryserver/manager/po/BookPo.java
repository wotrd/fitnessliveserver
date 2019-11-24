package com.example.libraryserver.manager.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 图书表(Businesses)实体类
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
     * 图书名字
     */
    private String name;
    /**
     * 图书类型
     */
    private String type;
    /**
     * 图书单价
     */
    private BigDecimal price;
    /**
     * 图书图片
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

    /**
     * 图书描述
     */
    private String remark;

}