package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "dutyInfo")
public class Dutyinfo implements Serializable {
    /**
     * 值班信息详细表Id
     */
    @Id
    @Column(name = "dutyInfoId")
    private Long dutyinfoid;

    /**
     * 值班日期
     */
    @Column(name = "dutyTime")
    private Date dutytime;

    /**
     * 值班描述
     */
    private String remarks;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Date updatetime;

    /**
     * 操作人员Id
     */
    @Column(name = "operatorId")
    private Long operatorid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取值班信息详细表Id
     *
     * @return dutyInfoId - 值班信息详细表Id
     */
    public Long getDutyinfoid() {
        return dutyinfoid;
    }

    /**
     * 设置值班信息详细表Id
     *
     * @param dutyinfoid 值班信息详细表Id
     */
    public void setDutyinfoid(Long dutyinfoid) {
        this.dutyinfoid = dutyinfoid;
    }

    /**
     * 获取值班日期
     *
     * @return dutyTime - 值班日期
     */
    public Date getDutytime() {
        return dutytime;
    }

    /**
     * 设置值班日期
     *
     * @param dutytime 值班日期
     */
    public void setDutytime(Date dutytime) {
        this.dutytime = dutytime;
    }

    /**
     * 获取值班描述
     *
     * @return remarks - 值班描述
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置值班描述
     *
     * @param remarks 值班描述
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取修改时间
     *
     * @return updateTime - 修改时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取操作人员Id
     *
     * @return operatorId - 操作人员Id
     */
    public Long getOperatorid() {
        return operatorid;
    }

    /**
     * 设置操作人员Id
     *
     * @param operatorid 操作人员Id
     */
    public void setOperatorid(Long operatorid) {
        this.operatorid = operatorid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", dutyinfoid=").append(dutyinfoid);
        sb.append(", dutytime=").append(dutytime);
        sb.append(", remarks=").append(remarks);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", operatorid=").append(operatorid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}