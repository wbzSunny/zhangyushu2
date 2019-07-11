package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 机构表
 * </p>
 *
 * @author ${author}
 * @since 2019-07-11
 */
@TableName("organization")
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构id
     */
    @TableId(value = "organization_id", type = IdType.AUTO)
    private Long organizationId;

    /**
     * 机构名
     */
    private String organizationName;

    /**
     * 机构logo_id
     */
    private Long organizationLogo;

    /**
     * 联系人id
     */
    private Long userId;

    /**
     * 联系人电话
     */
    private String userPhone;

    /**
     * 机构简介
     */
    private String organizationDesc;

    /**
     * 机构状态 1 审核中 2 通过 3 不通过
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;

    /**
     * 编辑时间
     */
    private LocalDateTime editTime;

    /**
     * 联系人
     */
    private String linkMan;


    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getOrganizationLogo() {
        return organizationLogo;
    }

    public void setOrganizationLogo(Long organizationLogo) {
        this.organizationLogo = organizationLogo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    @Override
    protected Serializable pkVal() {
        return this.organizationId;
    }

    @Override
    public String toString() {
        return "Organization{" +
        "organizationId=" + organizationId +
        ", organizationName=" + organizationName +
        ", organizationLogo=" + organizationLogo +
        ", userId=" + userId +
        ", userPhone=" + userPhone +
        ", organizationDesc=" + organizationDesc +
        ", status=" + status +
        ", creatTime=" + creatTime +
        ", editTime=" + editTime +
        ", linkMan=" + linkMan +
        "}";
    }
}
