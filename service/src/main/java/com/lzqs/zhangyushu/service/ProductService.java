package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 作品表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface ProductService extends IService<Product> {
    //点赞作品
    ResultInfo like(Long productId);
    // 评论商品
    ResultInfo commont(Long userId, String content, Long productId);
    //创建作品
    ResultInfo createProduct(HttpServletRequest request, Long userId, String desc,Long sampleReelsId );
}
