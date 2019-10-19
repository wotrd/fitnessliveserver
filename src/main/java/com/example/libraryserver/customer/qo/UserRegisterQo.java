package com.example.libraryserver.customer.qo;

import lombok.Data;

/**
 * 用户登陆查询类
 *
 * @author wangkaijin
 */
@Data
public class UserRegisterQo {

    /**
     * 手机号
     */
    private String mobileNum;
    /**
     * 密码
     */
    private String password;

}
