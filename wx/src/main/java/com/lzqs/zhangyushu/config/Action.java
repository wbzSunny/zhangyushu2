package com.lzqs.zhangyushu.config;

import com.lzqs.zhangyushu.safety.JwtUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class Action {
    public String checkToken(HttpServletRequest request){

        try {
            String token = request.getHeader("token");
            if (token.isEmpty() ||token == null){
                return "token 不能为空";
            }
            String userId = JwtUtil.getUserId(token);
            System.out.println("========================================================= "+ userId);
            if (userId == null){
                return "token 错误";
            }
        }catch (Exception e){
            return "系统错误";
        }
        return null;
    }
}
