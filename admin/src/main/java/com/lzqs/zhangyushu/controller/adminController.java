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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
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

    @PostMapping("login")
    @ResponseBody
    public ResultInfo login(@RequestBody Map<String, Object> map, HttpServletRequest request){
//        String account = map.get("account").toString();
//        String password = map.get("password").toString();
//        return adminService.login(account, password);
        return null;
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
