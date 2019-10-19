package com.example.libraryserver.customer.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author wotrd
 * @since 2019-10-01 17:13:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPo implements Serializable {

    private static final long serialVersionUID = 155586469882689233L;
    //ID
    private Long id;
    //账户
    private String account;
    //姓名
    private String name;
    //密码
    private String password;
    //性别（1男 0女）
    private Integer gender;
    //昵称
    private String nickName;
    //邮箱
    private String email;
    //身份证
    private String idcard;
    //手机号
    private String mobileNum;
    //角色（1管理员 0客户）
    private Integer role;
    //头像
    private String amatar;
    //备注
    private String remark;
    //注册时间
    private Date createTime;

}