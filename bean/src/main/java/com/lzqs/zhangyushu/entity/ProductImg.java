package com.lzqs.zhangyushu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 作品图片
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@TableName("product_img")
public class ProductImg extends Model<ProductImg> {

    private static final long serialVersionUID = 1L;

    /**
     * 作品图片id
     */
    @TableId(value = "product_img_id", type = IdType.AUTO)
    private Long productImgId;

    /**
     * 作品图片
     */
    private String productImg;

    /**
     * 作品id
     */
    private Long productId;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;


    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.productImgId;
    }

    @Override
    public String toString() {
        return "ProductImg{" +
        "productImgId=" + productImgId +
        ", productImg=" + productImg +
        ", productId=" + productId +
        ", addTime=" + addTime +
        "}";
    }
}
