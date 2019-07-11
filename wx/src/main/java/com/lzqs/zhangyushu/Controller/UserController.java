package com.lzqs.zhangyushu.Controller;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.lzqs.zhangyushu.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SampleReelsService sampleReelsService;

    @PostMapping("/userDetial")
    public ResultInfo userDetial(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token == null){
            return ResultInfo.failWithMsg("token 空");
        }
        String userId = JwtUtil.getUserId(token);
        if (userId == null){
            return ResultInfo.failWithMsg("token 错误");
        }
        return userService.userDetial(userId);
    }
    /**
     * 绑定手机号
     */
    @PostMapping("/bindingPhone")
    public  ResultInfo bindingPhone(HttpServletRequest request , @RequestBody Map<String,Object> map){

        try {
            String token = request.getHeader("token");
            if (token == null){
                return ResultInfo.failWithMsg("token 是空的");
            }
            String userID = JwtUtil.getUserId(token);
            if (userID == null){
                return  ResultInfo.failWithMsg("token 错误");
            }
            String phone = ParamTransformationUtils.transformToString(map.get("phone"));
            User user = userService.getById(Long.valueOf(userID));
            user.setPhone(phone);
            userService.updateById(user);
            return ResultInfo.success();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResultInfo.failWithMsg("系统错误");
        }
    }


    /**
     * 根据参数获取作品集列表
     */
    @PostMapping("/listSampleReelsByParam")
    public  ResultInfo ListSampleReels(HttpServletRequest request){
        String token = request.getHeader("token");
        if ( token == null){
            return ResultInfo.failWithMsg("token 是空的");
        }
        String userID = JwtUtil.getUserId(token);
        if (userID == null ){
            return ResultInfo.failWithMsg("token 错误");
        }

        return sampleReelsService.listSampleReelsByParam(userID);
    }


}
