package com.example.sceneserver.manager.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * info
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfoDO implements Serializable {
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 简介
     */
    private String summary;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}