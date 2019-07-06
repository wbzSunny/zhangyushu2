package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.dao.*;
import com.lzqs.zhangyushu.entity.*;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.ConfigBeanProp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 作品集表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class SampleReelsServiceImpl extends ServiceImpl<SampleReelsMapper, SampleReels> implements SampleReelsService {


    @Resource
    private SampleReelsMapper sampleReelsMapper;

    @Resource
    private CommonFileMapper commonFileMapper;

    @Resource
    private ConfigBeanProp configBeanProp;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductImgMapper productImgMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户id查看他的绘馆
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> listSampleReels(Long userId) {
        QueryWrapper<SampleReels> sampleReelsQueryWrapper = new QueryWrapper<>();
        sampleReelsQueryWrapper.lambda().eq(SampleReels::getUserId, userId).eq(SampleReels::getStatus, 1);
        List<SampleReels> sampleReelsList = sampleReelsMapper.selectList(sampleReelsQueryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!sampleReelsList.isEmpty()) {
            sampleReelsList.forEach(sampleReels -> {
                Map<String, Object> map = new HashMap<>();
                map.put("sampleReelsId", sampleReels.getSampleReelsId());
                map.put("sampleReelsName", sampleReels.getSampleReelsName());
                map.put("samoleReelsDesc", sampleReels.getSamoleReelsDesc());
                map.put("likeNum", sampleReels.getLikeNum());
                map.put("commentNum", sampleReels.getCommentNum());
                map.put("sampleReelsCover", configBeanProp.getFile_url() + commonFileMapper.selectById(sampleReels.getSampleReelsCover()).getFilePath());
                map.put("createTime", sampleReels.getCreateTime());
                mapList.add(map);
            });
        }
        return mapList;
    }

    /**
     * 根据作品集id查看所有作品
     *
     * @param sampleReelsId
     * @return
     */
    @Override
    public List<Map<String, Object>> listProduction(Long sampleReelsId) {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<>();
        productQueryWrapper.lambda().eq(Product::getSampleReelsId, sampleReelsId);
        List<Product> productList = productMapper.selectList(productQueryWrapper);
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (!productList.isEmpty()) {
            productList.forEach(product -> {
                Map<String, Object> map = new HashMap<>();
                map.put("productionId", product.getProductionId());
                map.put("productionName", product.getProductionName());
                map.put("likeNum", product.getLikeNum());
                map.put("commentNum", product.getCommentNum());
                map.put("createTime", product.getCreateTime());
                QueryWrapper<ProductImg> productImgQueryWrapper = new QueryWrapper<>();
                productImgQueryWrapper.lambda().eq(ProductImg::getProductId, product.getProductionId());
                List<ProductImg> productImgList = productImgMapper.selectList(productImgQueryWrapper);
                List<Map<String, Object>> imgList = new ArrayList<>();
                if (!productImgList.isEmpty()) {
                    productImgList.forEach(productImg -> {
                        Map<String, Object> imgMap = new HashMap<>();
                        imgMap.put("filePath", configBeanProp.getFile_url() + commonFileMapper.selectById(productImg.getProductImg()).getFilePath());
                        imgList.add(imgMap);
                    });
                    map.put("imgList", imgList);

                }
            });
        }
        return mapList;
    }


    /**
     * \根据作品id查看作品详情
     *
     * @param productionId
     * @return
     */
    @Override
    public Map<String, Object> getProduction(Long productionId) {
        Product product = productMapper.selectById(productionId);
        Map<String, Object> map = new HashMap<>();
        map.put("productionName", product.getProductionName());
        map.put("likeNum", product.getLikeNum());
        map.put("commentNum", product.getCommentNum());
        //查看作品图片
        QueryWrapper<ProductImg> productImgQueryWrapper = new QueryWrapper<>();
        productImgQueryWrapper.lambda().eq(ProductImg::getProductId, product.getProductionId());
        List<ProductImg> productImgList = productImgMapper.selectList(productImgQueryWrapper);
        List<Map<String, Object>> imgList = new ArrayList<>();
        if (!productImgList.isEmpty()) {
            productImgList.forEach(productImg -> {
                Map<String, Object> imgMap = new HashMap<>();
                imgMap.put("filePath", configBeanProp.getFile_url() + commonFileMapper.selectById(productImg.getProductImg()).getFilePath());
                imgList.add(imgMap);
            });
            map.put("imgList", imgList);
            map.put("productDesc", product.getProductDesc());
            //查看作品评论
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            commentQueryWrapper.lambda().eq(Comment::getBeCommentId, product.getProductionId()).eq(Comment::getStatus, 1);
            List<Comment> commentList = commentMapper.selectList(commentQueryWrapper);
            List<Map<String, Object>> commentContentList = new ArrayList<>();
            if (!commentList.isEmpty()) {
                commentList.forEach(comment -> {
                    Map<String, Object> commentContentMap = new HashMap<>();
                    commentContentMap.put("comment", comment.getCommentContent());
                    User user = userMapper.selectById(comment.getUserId());
                    commentContentMap.put("userName", user.getUserName());
                    commentContentMap.put("userHead", user.getUserHead());
                    commentContentList.add(commentContentMap);
                });
                map.put("commentContentList", commentContentList);
            }
        }
        return map;
    }
}
