package com.lzqs.zhangyushu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Admin;
import com.lzqs.zhangyushu.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lzqs.zhangyushu.config.Action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class adminController {
    private static final int ADD_ADMIN= 1;
    private static final int EDIT_ADMIN= 2;

    @Resource
    AdminService adminService;
    @Resource
    Action action;

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(@RequestBody Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String account = map.get("account")!= null? map.get("account").toString(): null;
        String mobile = map.get("mobile")!= null? map.get("mobile").toString() : null;
        String password = map.get("password").toString();

        if (account==null && mobile==null){
            return ResultInfo.failWithMsg("信息不能为空");
        }
        if (account!=null && account.trim().length() <4){
            return ResultInfo.failWithMsg("账号不能短于4个字");
        }
        if (mobile!=null && !action.check_regex(mobile, "^1[3|4|5|7|8][0-9]\\d{4,8}$")){
            return ResultInfo.failWithMsg("手机号不正确");
        }
        if (password.trim().length() <4){
            return ResultInfo.failWithMsg("密码不正确");
        }
        return adminService.login(account, mobile, action.encode_Key(password));
    }

    @PostMapping("/addAdmin")
    @ResponseBody
    public ResultInfo addAdmin (@RequestBody Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return addOrEditAdmin(map, request,ADD_ADMIN);
    }

    @PostMapping("/getAdmin")
    @ResponseBody
    public ResultInfo getAdmin (@RequestBody Map<String, Object> map, HttpServletRequest request){
      String checkAdmin = action.checkAdmin(request);
//        String checkAdmin =null;
        if (checkAdmin==null){
            Long adminId = map.get("adminId")!=null? Long.valueOf(map.get("adminId").toString()): null;
            String account = map.get("account")!=null? map.get("account").toString():null;
            try {
                Admin admin = adminId!=null? adminService.getById(adminId):
                        adminService.getOne(new QueryWrapper<Admin>().eq("account", account));
                return ResultInfo.success().add(admin);
            }catch (NullPointerException e){
                return ResultInfo.failWithMsg("账号不存在");
            }
        }
        return ResultInfo.failWithMsg(checkAdmin);
    }

    @PostMapping("/getAdminList")
    @ResponseBody
    public ResultInfo getAdminList (@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkAdmin = action.checkAdmin(request);
//        String checkAdmin =null;
        if (checkAdmin==null){
            List<Admin> adminList = adminService.list(new QueryWrapper<Admin>().ne("is_delete", 1));
            return ResultInfo.success().add(adminList);
        }
        return ResultInfo.failWithMsg(checkAdmin);
    }

    @PostMapping("/editAdmin")
    @ResponseBody
    public ResultInfo editAdmin(@RequestBody Map<String, Object> map, HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
       return addOrEditAdmin(map, request, EDIT_ADMIN);
    }

    @PostMapping("stopAdmin")
    @ResponseBody
    public ResultInfo stopAdmin(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkAdmin = action.checkAdmin(request);
//        String checkAdmin =null;
        if (checkAdmin==null){
            Long adminId = Long.valueOf(map.get("adminId").toString());
            Admin admin = adminService.getById(adminId);
            admin.setIsDelete(1);
            adminService.updateById(admin);
            return ResultInfo.success();
        }
        return ResultInfo.failWithMsg(checkAdmin);
    }

    @PostMapping("listProductCollection")
    @ResponseBody
    public ResultInfo listProductCollection(@RequestBody Map<String, Object> map, HttpServletRequest request){
        String checkAdmin = action.checkAdmin(request);
        if (checkAdmin==null){


        }
        return ResultInfo.failWithMsg(checkAdmin);
    }


    private ResultInfo addOrEditAdmin(Map<String, Object> map, HttpServletRequest request,int operation ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String checkAdmin = action.checkAdmin(request);
//        String checkAdmin =null;
        if (checkAdmin==null){
            String mobile = map.get("mobile")!=null? map.get("mobile").toString() : "";
            String name = map.get("name") !=null? map.get("name").toString() : "";
            String password = map.get("password")!= null? map.get("password").toString(): "";
            String description = map.get("description")!= null? map.get("description").toString() : "";
            String account = map.get("account")!= null? map.get("account").toString() :"";
            String phoneRegex = "^1[3|4|5|7|8][0-9]\\d{4,8}$"; // 手机号格式

            if (account.trim().isEmpty() || mobile.trim().isEmpty() || name.trim().isEmpty()){
                return ResultInfo.failWithMsg("账号，手机号或名称不能为空");
            }
            if (!action.check_regex(mobile, phoneRegex)){
                return ResultInfo.failWithMsg("手机号不正确");
            }
            if (account.trim().length()<4){
                return ResultInfo.failWithMsg("账号不能短于4个字");
            }
            if (password.trim().length()<4){
                return ResultInfo.failWithMsg("密码不能短于4个字");
            }
            if (operation==ADD_ADMIN){
                return adminService.addAdmin(mobile, action.encode_Key(password), description, account, name);
            }
            if (operation==EDIT_ADMIN){
                Long adminId = Long.valueOf(map.get("adminId").toString());
                return adminService.editAdmin(adminId, mobile, action.encode_Key(password), description, account, name);
            }
        }
        return ResultInfo.failWithMsg(checkAdmin);
    }

}
