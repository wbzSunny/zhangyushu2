package com.lzqs.zhangyushu.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.config.Action;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.entity.*;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/productCollection")
public class ProductCollectionController {
    @Resource
    SampleReelsService sampleReelsService;
    @Resource
    SampleReelsMapper sampleReelsMapper;
    @Resource
    CommonFileService commonFileService;
    @Resource
    ProductService productService;
    @Resource
    ProductImgService productImgService;
    @Resource
    Action action;

    @PostMapping("/listProductCollection")
    @ResponseBody
    public ResultInfo listProductCollection(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

        String checkToken = action.checkToken(request);
        if (checkToken==null){
//            Long userId = Long.valueOf(JwtUtil.getUserId(request.getHeader("token")));
            Long organizationId = Long.valueOf(map.get("organizationId").toString());
            List<SampleReels> sampleReels = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("organization_id", organizationId));
            if (sampleReels.isEmpty()) {
                return  ResultInfo.success().add("还没有任何作品集");
            }
            List<Map<String, Object>> listCollection = new ArrayList<>();
            for (SampleReels sampleReels1 : sampleReels){
                Map<String, Object> collection = new HashMap<>();
                String cover = commonFileService.getById(sampleReels1.getSampleReelsId()).getFilePath();
                collection.put("封面", cover);
                collection.put("作品集名称", sampleReels1.getSampleReelsName());
                collection.put("评论数", sampleReels1.getCommentNum());
                collection.put("点赞数", sampleReels1.getLikeNum());

                listCollection.add(collection);
            }
            return ResultInfo.success().add(listCollection);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    @PostMapping("/createCollection")
    @ResponseBody
    public  ResultInfo createCollection(@RequestParam Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
//        String checkToken =null;
        if (checkToken==null){
            Long userId = Long.valueOf(JwtUtil.getUserId(request.getHeader("token")));
//            Long userId = Long.valueOf(map.get("userId").toString());
            String sampleReelsName = map.get("sampleReelsName").toString();
            String description = map.get("description").toString();
            String studentName = map.get("studentName").toString();
            Integer status = Integer.valueOf(map.get("status").toString());
            SampleReels sampleReels = sampleReelsService.saveSample(request,userId, studentName, status, sampleReelsName, description);
            if (sampleReels==null){
                return ResultInfo.failWithMsg("请上传作品集封面照片");
            }
            return ResultInfo.success().add("创建成功");
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    @PostMapping("/collectionDetail")
    @ResponseBody
    public ResultInfo collectionDetail(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkToken = action.checkToken(request);
//        String checkToken =null;
        if (checkToken==null){
            Long collectionId = Long.valueOf(map.get("collectionId").toString());
            SampleReels sampleReels = sampleReelsService.getById(collectionId); // 获取作品集
            Map<String, Object> allInfo = new HashMap<>();
            Map<String, List> collections = new HashMap<>();
            allInfo.put("作品集名称", sampleReels.getSampleReelsName());

            List<Product> products = productService.list(new QueryWrapper<Product>().ne("is_delete", 1).eq("sample_reels_id", collectionId));//作品
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd HH:mm:ss");
            for (Product product : products){
                List productImages = new ArrayList();
                List<ProductImg> productImgs = productImgService.list(new QueryWrapper<ProductImg>().eq("product_id", product.getProductionId()));
                for (ProductImg productImg : productImgs){
                    CommonFile productFile = commonFileService.getById(productImg.getProductImgId());
                    productImages.add(productFile.getFilePath());
                }
                Map<String, Object> productDetail = new HashMap<>();
                productDetail.put("作品照片", productImages);
                productDetail.put("点赞数", product.getLikeNum());
                productDetail.put("评论数", product.getCommentNum());

                LocalDate time = product.getCreateTime().toLocalDate();
                List list = collections.containsKey(time.toString())? collections.get(time.toString()): new ArrayList();
                list.add(productDetail);
                collections.put(time.toString(), list);
            }
            allInfo.put("所有作品",collections);

            sampleReelsService.setView(collectionId);
            return ResultInfo.success().add(allInfo);
        }
        return ResultInfo.failWithMsg(checkToken);
    }

    @PostMapping("/setSharing")
    @ResponseBody
    public ResultInfo getSharing(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkError = action.checkToken(request);
        if (checkError==null){
            Long collectionId = Long.valueOf(map.get("collectionId").toString());
            sampleReelsService.setShareNumber(collectionId);
            return ResultInfo.success();
        }
        return ResultInfo.failWithMsg(checkError);
    }
}

//fdfdfdf