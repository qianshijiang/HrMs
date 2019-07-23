package com.hrms.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by QSJ on 2019/7/18.
 */
@Data
public class DutyInfoVo implements Serializable{

  /**
   * 值班详细Id
   */
  @Id
  private Long dutyInfoId;

  /**
   * 值班日期
   */
  //@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date dutyTime;

  /**
   * 值班描述
   */
  private String remarks;

  /**
   * 创建时间
   */
  //@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 修改时间
   */
  //@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

  /**
   * 操作人员Id
   */
  private Long operatorId;

  /**
   * 操作员名字
   */
  private String operName;

  /**
   * 员工Id
   */
  private Long empId;

  /**
   * 员工姓名
   */
  private String empName;

  /**
   * 员工工号
   */
  private String workId;

  /**
   * 员工Id集合
   */
  private List<Long> empIdList;
}
