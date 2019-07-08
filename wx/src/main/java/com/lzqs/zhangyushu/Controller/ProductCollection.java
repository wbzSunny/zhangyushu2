package com.lzqs.zhangyushu.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.entity.*;
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
@RequestMapping("productCollection")
public class ProductCollection {
    @Resource
    SampleReelsService sampleReelsService;
    @Resource
    UserService userService;
    @Resource
    SampleReelsMapper sampleReelsMapper;
    @Resource
    CommonFileService commonFileService;
    @Resource
    ProductService productService;
    @Resource
    ProductImgService productImgService;
    @Resource
    OrganizationService organizationService;
    @Resource
    ClewService clewService;

    @PostMapping("listProductCollection")
    @ResponseBody
    public ResultInfo listProductCollection(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

        Long userId = Long.valueOf(map.get("userId").toString());
        List<SampleReels> sampleReels = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("user_id", userId));
        if (sampleReels.isEmpty()) {
            return  ResultInfo.success().add("还没有任何作品集");
        }
        List<Map<String, Object>> listCollection = new ArrayList<>();
        Map<String, Object> collection;
        for (SampleReels sampleReels1 : sampleReels){
            collection = new HashMap<>();
            String cover = commonFileService.getById(sampleReels1.getSampleReelsId()).getFilePath();
            collection.put("封面", cover);
            collection.put("作品集名称", sampleReels1.getSampleReelsName());
            collection.put("评论数", sampleReels1.getCommentNum());
            collection.put("点赞数", sampleReels1.getLikeNum());

            listCollection.add(collection);
        }
        return ResultInfo.success().add(listCollection);
    }

    @PostMapping("/createCollection")
    @ResponseBody
    public  ResultInfo createCollection(@RequestPart Map<String, Object> map, HttpServletRequest request){
        Long userId = Long.valueOf(map.get("userId").toString());
        String sampleReelsName = map.get("sampleReelsName").toString();
        String description = map.get("description").toString();
        Integer status = Integer.valueOf(map.get("status").toString());
        SampleReels sampleReels = sampleReelsService.saveSample(request,userId, status, sampleReelsName, description);
        if (sampleReels==null){
            return ResultInfo.failWithMsg("请上传作品集封面照片");
        }
        return ResultInfo.success().add("创建成功");
    }

    @PostMapping("/collectionDetail")
    @ResponseBody
    public ResultInfo collectionDetail(@RequestBody Map<String, Object> map, HttpServletRequest request){
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
        return ResultInfo.success().add(allInfo);
    }

    @PostMapping("/organizationDetail") //// 未完成
    @ResponseBody
    public ResultInfo organizationDetail(@RequestBody Map<String, Object> map, HttpServletRequest request){
        Long organizationId = Long.valueOf(map.get("organizationId").toString());

        Map<String, Object> allInfo = new HashMap<>();
        Organization organization = organizationService.getById(organizationId);
        allInfo.put("机构名", organization.getOrganizationName());
        allInfo.put("logo", commonFileService.getById(organization.getOrganizationLogo()).getFilePath());
        List<Clew> clews = clewService.list(new QueryWrapper<Clew>().eq("organization_id", organizationId));
        allInfo.put("招生线索", clews.size());

        //// 未完成
        return  ResultInfo.success().add(allInfo);
    }

    @PostMapping("/organizationRegistrationIndex")
    @ResponseBody
    public ResultInfo organizationRegistrationIndex(@RequestBody Map<String, Object> map, HttpServletRequest request){

        Long organizationId = Long.valueOf(map.get("organizationId").toString());
        List<Clew> clews = clewService.list(new QueryWrapper<Clew>().eq("organization_id", organizationId));
        Map<String, Object> allInfo = new HashMap<>();
        Map<String, List> clewsMap = new HashMap<>();
        int size = 0;
        for (Clew clew : clews){
            LocalDate time = clew.getCreateTime().toLocalDate();
            Map<String, Object> studentDetail = new HashMap<>();
            studentDetail.put("学员姓名", clew.getStudentName());
            studentDetail.put("最后跟进时间", clew.getEditTime());
            List list = clewsMap.containsKey(time.toString())? clewsMap.get(time.toString()) : new ArrayList();
            list.add(studentDetail.put("新增家长咨询", list.size()+1));
            clewsMap.put(time.toString(), list);
            size = list.size();
        }
        return ResultInfo.success().add(clewsMap);
    }

}
