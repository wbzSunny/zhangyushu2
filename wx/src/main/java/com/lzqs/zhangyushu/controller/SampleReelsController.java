package com.lzqs.zhangyushu.Controller;


import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.SampleReelsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/sampleReels")
public class SampleReelsController {

    @Resource
    private SampleReelsService sampleReelsService;

    /**
     * 根据用户id查看他的绘馆
     *
     * @param map
     * @return
     */
    @PostMapping("/listSampleReels")
    @ResponseBody
    public ResultInfo listSampleReels(@RequestBody Map<String, Object> map) {
        Long userId = Long.valueOf(map.get("userId").toString());
        return ResultInfo.success().add(sampleReelsService.listSampleReels(userId));
    }


    /**
     * 根据作品集id查看所有作品
     *
     * @param map
     * @return
     */
    @PostMapping("/listProduction")
    @ResponseBody
    public ResultInfo listProduction(@RequestBody Map<String, Object> map) {
        Long sampleReelsId = Long.valueOf(map.get("sampleReelsId").toString());
        return ResultInfo.success().add(sampleReelsService.listProduction(sampleReelsId));
    }

    /**
     * 根据作品id查看作品详情
     *
     * @param map
     * @return
     */
    @PostMapping("/getProduction")
    @ResponseBody
    public ResultInfo getProduction(@RequestBody Map<String, Object> map) {
        Long productionId = Long.valueOf(map.get("productionId").toString());
        return ResultInfo.success().add(sampleReelsService.getProduction(productionId));
    }

    /**
     * 作品集点赞
     */
    @PostMapping("/like")
    @ResponseBody
    public ResultInfo like(@RequestBody Map<String ,Object> map){
        Long sampleReelsId = ParamTransformationUtils.transformToNonNegativeLong(map.get("sampleReelsId"));
        return sampleReelsService.like(sampleReelsId);
    }
    /**
     * 评论
     */
    @PostMapping("/commont")
    @ResponseBody
    public  ResultInfo commont(@RequestBody Map<String,Object> map, HttpServletRequest request){

        String token   = request.getHeader("token");
        if ( token == null){
            return ResultInfo.failWithMsg("token 空");
        }
        String userId = JwtUtil.getUserId(token);
        if (userId == null){
            return ResultInfo.failWithMsg("token 错误");
        }
        String content = ParamTransformationUtils.transformToString(map.get("content"));
        Long sampleReelsId = ParamTransformationUtils.transformToNonNegativeLong(map.get("sampleReelsId"));
        return sampleReelsService.sampleReelsId(Long.valueOf(userId),content,sampleReelsId);


    }


}
