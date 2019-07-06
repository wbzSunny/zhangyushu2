package com.lzqs.zhangyushu.common;

public class UrlCon {
    private String filePath;
    private String path;
    //文件默认生成状态 ： 0未生成  1 已生成   2 出现错误
    private Integer status;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
