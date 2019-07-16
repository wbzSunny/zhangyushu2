package com.lzqs.zhangyushu.Controller;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.paramUtil.ParamCheckUtils;
import com.lzqs.zhangyushu.paramUtil.ParamTransformationUtils;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.UserService;
import com.lzqs.zhangyushu.wxLogin.WeChatLogin;
import com.lzqs.zhangyushu.wxLogin.WxLoginVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginContorller {
    @Resource
    private UserService userService;

    /**
     *微信 登录
     */
    @PostMapping("/login")
    public ResultInfo login(@RequestBody Map<String ,Object> map){
        try {
            String code = map.get("code").toString();
            Long inviteId =Long.valueOf(map.get("inviteId").toString());
            if (ParamCheckUtils.paramIsNull(code)){
                return  ResultInfo.failWithMsg("code 不能是空");
            }
            WxLoginVo wxLoginVo = WeChatLogin.login(code);
            if(wxLoginVo.getOpenid() != null){
                User user;
                if (inviteId != null){
                    //邀请人id 不是空 判断邀请人是不是商户 是商户 将当前登录的 用户 变更用户状态是商户学员
                    user = userService.editUserStatus(wxLoginVo.getOpenid(),inviteId);

                }else {
                    System.out.println("====================微信的 openId============" + wxLoginVo.getOpenid());
                     user = userService.queryUserByOpenId(wxLoginVo.getOpenid());
                }
                    String token = JwtUtil.sign(user.getUserId().toString());
                    user.setToken(token);
                    return ResultInfo.success().add(user);

            }else {
                return ResultInfo.failWithMsg("code 错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.failWithMsg("系统错误");
        }
    }
    /**
     * 从前端获取 用户信息 更新用户信息
     */
    @PostMapping("/updateUserInfo")
    public  ResultInfo updateUserInfo(HttpServletRequest request, @RequestBody Map<String, Object> map){
        try {
            String token = request.getHeader("token");
            if (token == null){
                return ResultInfo.failWithMsg("token 是空的");
            }

            String userId = JwtUtil.getUserId(token);
            if (userId== null){
                return ResultInfo.failWithMsg("token 错误");
            }
            String userHead = ParamTransformationUtils.transformToString(map.get("userHead"));
            String nickName = ParamTransformationUtils.transformToString(map.get("nickName"));
            Integer gender = ParamTransformationUtils.transformToInteger(map.get("gender"));
            return userService.updateByInfo(Long.valueOf(userId),userHead,nickName,gender);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultInfo.failWithMsg("系统错误");
        }
    }

}
