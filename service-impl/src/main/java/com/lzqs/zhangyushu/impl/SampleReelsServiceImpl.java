package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzqs.zhangyushu.common.ConfigBeanProp;
import com.lzqs.zhangyushu.dao.*;
import com.lzqs.zhangyushu.entity.*;
import com.lzqs.zhangyushu.service.CommonFileService;
import com.lzqs.zhangyushu.service.SampleReelsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
    @Resource
    CommonFileService commonFileService;
    @Resource
    OrganizationMapper organizationMapper;

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

    public SampleReels saveSample(HttpServletRequest request, Long userId, String studentName, Integer status, String sampleReelsName, String description) {

        List<CommonFile> commonFiles = commonFileService.uploadFile(request);
        if (commonFiles.isEmpty()){
            return null;
        }
        CommonFile commonFile = commonFiles.get(0);
        SampleReels sampleReels = new SampleReels();
        sampleReels.setUserId(userId);
        sampleReels.setUserName(userMapper.selectById(userId).getUserName());
        sampleReels.setSampleReelsName(sampleReelsName);
        sampleReels.setSampleReelsCover(commonFile.getId());
        sampleReels.setSamoleReelsDesc(description);
        sampleReels.setStudentName(studentName);
        sampleReels.setOrganizationId(organizationMapper.selectOne(new QueryWrapper<Organization>().eq("user_id", userId)).getOrganizationId());
        sampleReels.setCommentNum(0);
        sampleReels.setLikeNum(0);
        sampleReels.setViewNum(Long.valueOf(0));
        sampleReels.setCreateTime(LocalDateTime.now());
        sampleReels.setStatus(status);

        sampleReelsMapper.insert(sampleReels);
        return sampleReels;
    }

    @Override
    public List getGallery(Long organizationId) {
        List<SampleReels> sampleReelsList = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("organization_id", organizationId));
        List gallery= new ArrayList<>();
        for (SampleReels sampleReels : sampleReelsList){
            gallery.add(commonFileMapper.selectById(sampleReels.getSampleReelsCover()).getFilePath());
        }
        return gallery;
    }

    public Map<String,Object> getNumber(Long organizationId) {
        List<SampleReels> sampleReels = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("organization_id", organizationId));
        long viewNumber = 0, likeNumber=0, forwardingNumber=0, commentNumber=0;
        for (SampleReels sampleReels1 : sampleReels){
            viewNumber += sampleReels1.getViewNum();
            likeNumber += sampleReels1.getLikeNum();
            forwardingNumber += sampleReels1.getForwardingNumb();
            commentNumber += sampleReels1.getCommentNum();
        }
        Map<String,Object> infMap = new HashMap<>();
        infMap.put("view", viewNumber);
        infMap.put("like", likeNumber);
        infMap.put("forward", forwardingNumber);
        infMap.put("comment", commentNumber);
        infMap.put("size", sampleReels.size());

        return infMap;
    }

    //dsdsdsdsds
    public void setView(Long sampleReelsId) {
        synchronized (sampleReelsId){
            SampleReels sampleReels = sampleReelsMapper.selectById(sampleReelsId);
            long viewNumber = sampleReels.getViewNum();
            sampleReels.setViewNum(viewNumber+1);
            sampleReelsMapper.updateById(sampleReels);
        }
    }

}
