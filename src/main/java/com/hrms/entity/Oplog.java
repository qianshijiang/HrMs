package com.hrms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "oplog")
public class Oplog implements Serializable {
    @Id
    private Integer id;

    /**
     * 添加日期（操作日期）
     */
    @Column(name = "addDate")
    private Date adddate;

    /**
     * 操作内容
     */
    private String operate;

    /**
     * 操作员ID
     */
    private Integer hrid;

    /**
     * 操作Ip
     */
    private String ip;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取添加日期（操作日期）
     *
     * @return addDate - 添加日期（操作日期）
     */
    public Date getAdddate() {
        return adddate;
    }

    /**
     * 设置添加日期（操作日期）
     *
     * @param adddate 添加日期（操作日期）
     */
    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    /**
     * 获取操作内容
     *
     * @return operate - 操作内容
     */
    public String getOperate() {
        return operate;
    }

    /**
     * 设置操作内容
     *
     * @param operate 操作内容
     */
    public void setOperate(String operate) {
        this.operate = operate == null ? null : operate.trim();
    }

    /**
     * 获取操作员ID
     *
     * @return hrid - 操作员ID
     */
    public Integer getHrid() {
        return hrid;
    }

    /**
     * 设置操作员ID
     *
     * @param hrid 操作员ID
     */
    public void setHrid(Integer hrid) {
        this.hrid = hrid;
    }

    /**
     * 获取操作Ip
     *
     * @return ip - 操作Ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置操作Ip
     *
     * @param ip 操作Ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adddate=").append(adddate);
        sb.append(", operate=").append(operate);
        sb.append(", hrid=").append(hrid);
        sb.append(", ip=").append(ip);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}