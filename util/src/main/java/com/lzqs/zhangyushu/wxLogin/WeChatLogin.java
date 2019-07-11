package com.lzqs.zhangyushu.wxLogin;

import com.alibaba.fastjson.JSONObject;

import static com.lzqs.zhangyushu.httpRequest.HttpRequest.httpRequest;

/**
 * Created by JinZhicheng on 2018/12/29 12:43
 */
public class WeChatLogin {


    private final static String APP_ID = "wxac2ffac6983152db";
    private final static String APP_SECRET = "ed3fed1882c7722a6ea74058b9cf087a";

    public static WxLoginVo login(String code) {
        WxLoginVo wxLoginVo = new WxLoginVo();
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID +
                    "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";
            //进行网络请求,访问url接口
            String weixin = httpRequest(url, null);
            System.out.println("-------------weixin------------------" + weixin);
            //进行网络请求,访问url接口
            JSONObject result = JSONObject.parseObject(weixin);
            System.out.println("result============" + result);
            wxLoginVo.setOpenid(result.getString("openid"));
            wxLoginVo.setSession_key(result.getString("session_key"));
            wxLoginVo.setUnionid(result.getString("unionid"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxLoginVo;
    }

    public static void main(String[] args) {
        System.out.println(login("0238mwPf24RJhJ0IbINf2QuhPf28mwPK"));
    }

}
