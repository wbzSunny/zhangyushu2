package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 线索表 （潜在用户）
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@TableName("clew")
public class Clew extends Model<Clew> {

    private static final long serialVersionUID = 1L;

    /**
     * 线索id
     */
    @TableId(value = "clew_id", type = IdType.AUTO)
    private Long clewId;

    /**
     * 学员姓名
     */
    private String studentName;

    /**
     * 家长电话
     */
    private String parentPhone;

    private String remark;

    /**
     * 线索产生时间
     */
    private LocalDateTime createTime;

    /**
     * 跟进次数
     */
    private Integer followUpNum;

    /**
     * 最后跟进时间
     */
    private LocalDateTime editTime;


    public Long getClewId() {
        return clewId;
    }

    public void setClewId(Long clewId) {
        this.clewId = clewId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getFollowUpNum() {
        return followUpNum;
    }

    public void setFollowUpNum(Integer followUpNum) {
        this.followUpNum = followUpNum;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.clewId;
    }

    @Override
    public String toString() {
        return "Clew{" +
        "clewId=" + clewId +
        ", studentName=" + studentName +
        ", parentPhone=" + parentPhone +
        ", remark=" + remark +
        ", createTime=" + createTime +
        ", followUpNum=" + followUpNum +
        ", editTime=" + editTime +
        "}";
    }
}
