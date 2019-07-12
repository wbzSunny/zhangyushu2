package com.lzqs.zhangyushu.controller;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Admin;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.AdminService;
import com.lzqs.zhangyushu.service.ClewService;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.lzqs.zhangyushu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lzqs.zhangyushu.config.Action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Resource
    UserService userService;
    @Resource
    SampleReelsService sampleReelsService;
    @Resource
    ClewService clewService;
    @Resource
    AdminService adminService;
    @Resource
    Action action;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(@RequestBody Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String account = map.get("account").toString();
        String password = map.get("password").toString();
//        if (account.trim().isEmpty()){
//            return ResultInfo.failWithMsg("账号不能为空");
//        }
        if (account.trim().length() <4){
            return ResultInfo.failWithMsg("账号不能短于4个字");
        }
        if (password.trim().isEmpty()){
            return ResultInfo.failWithMsg("密码不能为空");
        }
        return adminService.login(account, action.encode_Key(password));
    }

    @PostMapping("/addAdmin")
    @ResponseBody
    public ResultInfo addAdmin (@RequestBody Map<String, Object> map, HttpServletRequest request){
        String mobile = map.get("mobile").toString();
        String name = map.get("name").toString();
        String password = map.get("password").toString();
        String description = map.get("description").toString();
        String account = map.get("account").toString();
        return null;
        //

    }


//
//    public String checkToken(HttpServletRequest request){
//        try {
//            String token = request.getHeader("token");
//            if (token.isEmpty() ||token == null){
//                return "token 不能为空";
//            }
//            String userId = JwtUtil.getUserId(token);
//            System.out.println("========================================================= "+ userId);
//            if (userId == null){
//                return "token 错误";
//            }
//        }catch (Exception e){
//            return "系统错误";
//        }
//        return null;
//    }
}
