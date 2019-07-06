package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 评论列表
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@TableName("comment")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论人id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime creatTime;

    /**
     *  1 作品评论 2 作品集评论
     */
    private Long status;

    /**
     * 被评论id
     */
    private Long beCommentId;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getBeCommentId() {
        return beCommentId;
    }

    public void setBeCommentId(Long beCommentId) {
        this.beCommentId = beCommentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
        "commentId=" + commentId +
        ", commentContent=" + commentContent +
        ", userId=" + userId +
        ", creatTime=" + creatTime +
        ", status=" + status +
        ", beCommentId=" + beCommentId +
        "}";
    }
}
