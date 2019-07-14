package com.lzqs.zhangyushu.config;

import com.lzqs.zhangyushu.safety.JwtUtil;
import com.lzqs.zhangyushu.service.AdminService;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Action {
    @Resource
    AdminService adminService;
    private static final String MD5_key = "MD5";

    public String checkToken(HttpServletRequest request){

        try {
            String token = request.getHeader("token");
            if (token.isEmpty() ||token == null){
                return "token 不能为空";
            }
            String userId = JwtUtil.getUserId(token);
            if (userId == null){
                return "token 错误";
            }
        }catch (Exception e){
            return "系统错误";
        }
        return null;
    }
    public String checkAdmin(HttpServletRequest request){
        try {
            String token = request.getHeader("token");
            if (token.isEmpty() ||token == null){
                return "token 不能为空";
            }
            String userId = JwtUtil.getUserId(token);
            if (userId == null){
                return "token 错误";
            }
            if (adminService.getById(Long.valueOf(userId))==null){
                return "先登录为管理员";
            }
        }catch (Exception e){
            return "系统错误";
        }
        return null;
    }

    public String encode_Key(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance(MD5_key);
        BASE64Encoder base64en = new BASE64Encoder();
        String result = base64en.encode(md5.digest(password.getBytes("utf-8")));
        return result;
    }

    public boolean check_regex(String str, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
