package com.example.sceneserver.customer.qo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登陆查询类
 *
 * @author wangkaijin
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
