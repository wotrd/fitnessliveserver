package com.example.animalsalesserver.conf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangkaijin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespVo<T> {

    /**
     * code 返回码
     */
    private String code;
    /**
     * msg 返回信息
     */
    private String msg;
    /**
     * data 返回数据
     */
    private T data;

}
