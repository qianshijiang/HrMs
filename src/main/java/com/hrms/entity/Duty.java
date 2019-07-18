package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "duty")
public class Duty implements Serializable {
    /**
     * 值班表Id
     */
    @Id
    private Integer id;

    /**
     * 值班信息表Id
     */
    @Column(name = "dutyInfoId")
    private Integer dutyinfoid;

    /**
     * 人员Id(员工Id）
     */
    @Column(name = "employeeId")
    private Integer employeeid;

    /**
     * 创建时间
     */
    @Column(name = "createDate")
    private Date createdate;

    /**
     * 修改时间
     */
    @Column(name = "updateDate")
    private Date updatedate;

    private static final long serialVersionUID = 1L;

    /**
     * 获取值班表Id
     *
     * @return id - 值班表Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置值班表Id
     *
     * @param id 值班表Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取值班信息表Id
     *
     * @return dutyInfoId - 值班信息表Id
     */
    public Integer getDutyinfoid() {
        return dutyinfoid;
    }

    /**
     * 设置值班信息表Id
     *
     * @param dutyinfoid 值班信息表Id
     */
    public void setDutyinfoid(Integer dutyinfoid) {
        this.dutyinfoid = dutyinfoid;
    }

    /**
     * 获取人员Id(员工Id）
     *
     * @return employeeId - 人员Id(员工Id）
     */
    public Integer getEmployeeid() {
        return employeeid;
    }

    /**
     * 设置人员Id(员工Id）
     *
     * @param employeeid 人员Id(员工Id）
     */
    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    /**
     * 获取创建时间
     *
     * @return createDate - 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置创建时间
     *
     * @param createdate 创建时间
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 获取修改时间
     *
     * @return updateDate - 修改时间
     */
    public Date getUpdatedate() {
        return updatedate;
    }

    /**
     * 设置修改时间
     *
     * @param updatedate 修改时间
     */
    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dutyinfoid=").append(dutyinfoid);
        sb.append(", employeeid=").append(employeeid);
        sb.append(", createdate=").append(createdate);
        sb.append(", updatedate=").append(updatedate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}