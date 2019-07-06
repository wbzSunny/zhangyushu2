package com.lzqs.zhangyushu.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.lzqs.zhangyushu.service.UserService;
import common.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    @Resource
//    CommonFileMapper commonFileMapper;

    @PostMapping("listProductCollection")
    @ResponseBody
    public ResultInfo listProductCollection(@RequestBody Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {

        String userId = map.get("userId").toString();
//        List<SampleReels> sampleReels = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("user_id", userId));
//        if (sampleReels.isEmpty()) {
//            return  ResultInfo.success().add("还没有任何作品集");
//        }
//        Map<String, Object> listCollection = new HashMap<>();
//        for (SampleReels sampleReels1 : sampleReels){
//            listCollection.put("封面","" );
//        }

return null;
    }
}
