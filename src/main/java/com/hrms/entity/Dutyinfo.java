package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Table(name = "dutyInfo")
@Data
public class Dutyinfo implements Serializable {
    /**
     * 值班信息详细表Id
     */
    @Id
    @Column(name = "dutyInfoId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long dutyInfoId;

    /**
     * 值班日期
     */
    @Column(name = "dutyTime")
    private Date dutyTime;

    /**
     * 值班描述
     */
    private String remarks;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Date updateTime;

    /**
     * 操作人员Id
     */
    @Column(name = "operatorId")
    private Long operatorId;

}