package com.lzqs.zhangyushu.impl;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.dao.CommentMapper;
import com.lzqs.zhangyushu.dao.ProductImgMapper;
import com.lzqs.zhangyushu.entity.Comment;
import com.lzqs.zhangyushu.entity.CommonFile;
import com.lzqs.zhangyushu.entity.Product;
import com.lzqs.zhangyushu.dao.ProductMapper;
import com.lzqs.zhangyushu.entity.ProductImg;
import com.lzqs.zhangyushu.service.CommonFileService;
import com.lzqs.zhangyushu.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    private ProductImgMapper productImgMapper;
    @Resource
    private CommonFileService commonFileService;

    /**
     * 点赞商品
     * @param productId
     * @return
     */
    @Override
    public ResultInfo like(Long productId) {
        Product product = productMapper.selectById(productId);
        product.setLikeNum(product.getLikeNum()+1);
        productMapper.updateById(product);
        return ResultInfo.success();
    }

    /**
     * 评论数
     * @param userId
     * @param content
     * @param productId
     * @return
     */
    @Override
    public ResultInfo commont(Long userId, String content, Long productId) {
        // 1 增加评论数
        Product product = productMapper.selectById(productId);
        product.setCommentNum(product.getCommentNum()+1);
        productMapper.updateById(product);
        Comment comment = new Comment();
        comment.setCommentContent(content);
        comment.setBeCommentId(productId);
        comment.setCreatTime(LocalDateTime.now());
        comment.setUserId(userId);
        comment.setStatus(1l);
        commentMapper.insert(comment);

        return ResultInfo.success();
    }

    /**
     * 创建作品
     * @param request
     * @param userId
     * @param desc
     * @return
     */
    @Override
    public ResultInfo createProduct(HttpServletRequest request, Long userId, String desc,Long sampleReelsId ) {

        List<CommonFile> commonFiles = commonFileService.uploadFile(request);
        if (commonFiles.isEmpty()){
            return null;
        }

        Product product = new Product();
        product.setCommentNum(0);;
        product.setLikeNum(0);
        product.setCreateTime(LocalDateTime.now());
        product.setProductDesc(desc);
        if (sampleReelsId != null){
            product.setSampleReelsId(sampleReelsId);
        }
        productMapper.insert(product);
        if (commonFiles != null && commonFiles.size() != 0){
            for (CommonFile commonFile : commonFiles){
                ProductImg productImg = new ProductImg();
                productImg.setAddTime(LocalDateTime.now());
                productImg.setProductId(product.getProductionId());
                productImg.setProductImgId(commonFile.getId());
                productImgMapper.insert(productImg);
            }
        }
        return ResultInfo.success();
    }

}
