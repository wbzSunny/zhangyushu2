package com.lzqs.zhangyushu.common;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.Serializable;

/**
 * 通用的返回类
 **/
public class ResultInfo implements Serializable {
    //状态码 100-成功，200-失败
    private int code;
    //提示信息
    private String msg;
    //用户要返回给浏览器的数据
    private Object data;


    public static ResultInfo success() {
        ResultInfo result = new ResultInfo();
        result.setCode(100);
        result.setMsg("成功");
        return result;
    }

    public static ResultInfo fail() {
        ResultInfo result = new ResultInfo();
        result.setCode(200);
        result.setMsg("系统错误");
        return result;
    }

    public static ResultInfo failWithMsg(String newmsg) {
        ResultInfo result = new ResultInfo();
        result.setCode(400);
        result.setMsg(newmsg);
        return result;
    }


    /**
     * 失败 + 自定义msg,缺省为"处理失败"
     *
     * @param newmsg
     * @return
     */
    public static ResultInfo failWithMsg(int code, String newmsg) {
        ResultInfo result = new ResultInfo();
        result.setCode(code);
        if (StringUtils.isEmpty(newmsg)) {
            newmsg = "处理失败";
        }
        result.setMsg(newmsg);
        return result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultInfo add(Object data) {
        this.setData(data);
        return this;
    }

}
