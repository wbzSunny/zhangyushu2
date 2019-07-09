package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 作品集表
 * </p>
 *
 * @author ${author}
 * @since 2019-07-09
 */
@TableName("sample_reels")
public class SampleReels extends Model<SampleReels> {

    private static final long serialVersionUID = 1L;

    /**
     * 作品集id
     */
    @TableId(value = "sample_reels_id", type = IdType.AUTO)
    private Long sampleReelsId;

    /**
     * 创建者id
     */
    private Long userId;

    /**
     * 创建者姓名
     */
    private String userName;

    /**
     * 作品集名称
     */
    private String sampleReelsName;

    /**
     * 作品集封面id
     */
    private Long sampleReelsCover;

    /**
     * 作品集描述
     */
    private String samoleReelsDesc;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否公开状态 1 公开 2 不公开
     */
    private Integer status;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 查看数
     */
    private Long viewNum;

    /**
     * 机构id
     */
    private Long organizationId;

    /**
     * 学员姓名
     */
    private String studentName;


    public Long getSampleReelsId() {
        return sampleReelsId;
    }

    public void setSampleReelsId(Long sampleReelsId) {
        this.sampleReelsId = sampleReelsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSampleReelsName() {
        return sampleReelsName;
    }

    public void setSampleReelsName(String sampleReelsName) {
        this.sampleReelsName = sampleReelsName;
    }

    public Long getSampleReelsCover() {
        return sampleReelsCover;
    }

    public void setSampleReelsCover(Long sampleReelsCover) {
        this.sampleReelsCover = sampleReelsCover;
    }

    public String getSamoleReelsDesc() {
        return samoleReelsDesc;
    }

    public void setSamoleReelsDesc(String samoleReelsDesc) {
        this.samoleReelsDesc = samoleReelsDesc;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Long getViewNum() {
        return viewNum;
    }

    public void setViewNum(Long viewNum) {
        this.viewNum = viewNum;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    protected Serializable pkVal() {
        return this.sampleReelsId;
    }

    @Override
    public String toString() {
        return "SampleReels{" +
        "sampleReelsId=" + sampleReelsId +
        ", userId=" + userId +
        ", userName=" + userName +
        ", sampleReelsName=" + sampleReelsName +
        ", sampleReelsCover=" + sampleReelsCover +
        ", samoleReelsDesc=" + samoleReelsDesc +
        ", createTime=" + createTime +
        ", status=" + status +
        ", likeNum=" + likeNum +
        ", commentNum=" + commentNum +
        ", viewNum=" + viewNum +
        ", organizationId=" + organizationId +
        ", studentName=" + studentName +
        "}";
    }
}
