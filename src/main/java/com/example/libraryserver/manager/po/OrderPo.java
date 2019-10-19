package com.example.libraryserver.manager.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * (Orders)实体类
 *
 * @author wotrd
 * @since 2019-10-04 15:54:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderPo implements Serializable {
    private static final long serialVersionUID = -93344554150012791L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 商品名字
     */
    private String bName;
    /**
     * 商品数量
     */
    private Integer bCount;
    /**
     * 商品单价
     */
    private BigDecimal bPrice;
    /**
     * 商品订单总价
     */
    private BigDecimal totalPrice;
    /**
     * 买家ID
     */
    private Long buyerId;
    /**
     * 卖家ID
     */
    private Long sellId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 卖家名字
     */
    private String sellName;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 订单状态（1已完成 2未完成 3已取消）
     */
    private Integer status;

}