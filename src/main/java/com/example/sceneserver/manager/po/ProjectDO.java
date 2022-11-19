package com.example.sceneserver.manager.po;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * project
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 项目名字
     */
    private String projectName;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 项目区域
     */
    private String areaName;

    /**
     * 保护单位
     */
    private String protectUnit;

    /**
     * 申请单位
     */
    private String applyUnit;

    /**
     * 简介
     */
    private String remark;

    /**
     * 项目类别
     */
    private String category;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}