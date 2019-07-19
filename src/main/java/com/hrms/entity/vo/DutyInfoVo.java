package com.hrms.entity.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import lombok.Data;

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
  private Date dutyTime;

  /**
   * 值班描述
   */
  private String remarks;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 修改时间
   */
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
  private Integer empId;

  /**
   * 员工姓名
   */
  private String empName;

  /**
   * 员工工号
   */
  private String workId;

}
