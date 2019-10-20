package com.example.hospital.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Patient)实体类
 *
 * @author wotrd
 * @since 2019-06-10 13:29:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable {
    private static final long serialVersionUID = 114415820130446349L;
    //主键id
    private Long id;
    //账户
    private String account;
    //密码
    private String password;
    //名字
    private String name;
    //性别（0男1女）
    private Integer gender;
    //年龄
    private Integer age;
    //是否登录（0未登录1已登录）
    private Integer isLogin;
    //学号
    private Long sId;
    //创建时间
    private Date createTime;

}