package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Table(name = "oplog")
@Data
public class Oplog implements Serializable {
    @Id
    private Long id;

    /**
     * 添加日期（操作日期）
     */
    @Column(name = "addDate")
    private Date addDate;

    /**
     * 操作内容
     */
    private String operate;

    /**
     * 操作员ID
     */
    private Long hrid;

    private String hrName;

    /**
     * 操作Ip
     */
    private String ip;
}