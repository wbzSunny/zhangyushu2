package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 作品表
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@TableName("product")
public class Product extends Model<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 作品id
     */
    @TableId(value = "production_id", type = IdType.AUTO)
    private Long productionId;

    /**
     * 作品名字
     */
    private String productionName;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 作品描述
     */
    private String productDesc;

    /**
     * 作品集id
     */
    private Long sampleReelsId;


    public Long getProductionId() {
        return productionId;
    }

    public void setProductionId(Long productionId) {
        this.productionId = productionId;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Long getSampleReelsId() {
        return sampleReelsId;
    }

    public void setSampleReelsId(Long sampleReelsId) {
        this.sampleReelsId = sampleReelsId;
    }

    @Override
    protected Serializable pkVal() {
        return this.productionId;
    }

    @Override
    public String toString() {
        return "Product{" +
        "productionId=" + productionId +
        ", productionName=" + productionName +
        ", likeNum=" + likeNum +
        ", commentNum=" + commentNum +
        ", createTime=" + createTime +
        ", productDesc=" + productDesc +
        ", sampleReelsId=" + sampleReelsId +
        "}";
    }
}
