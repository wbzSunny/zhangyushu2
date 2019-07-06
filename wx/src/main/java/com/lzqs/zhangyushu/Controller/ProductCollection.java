package com.lzqs.zhangyushu.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.dao.CommonFileMapper;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.entity.CommonFile;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.service.CommonFileService;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.lzqs.zhangyushu.service.UserService;
import common.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("createCollection")
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

}
