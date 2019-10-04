package com.example.animalsalesserver.customer.qo;

import lombok.Builder;
import lombok.Data;

/**
 * 用户登陆查询类
 *
 * @author wangkaijin
 */
@Builder
@Data
public class UserLoginQo {

    /**
     * 账户
     */
    private String account;

    /**
     * 手机号
     */
    private String mobileNum;
    /**
     * 密码
     */
    private String password;

}
