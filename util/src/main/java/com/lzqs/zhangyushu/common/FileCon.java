package com.lzqs.zhangyushu.common;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "config")
@PropertySource(value = {"classpath:config.properties"})
@Component
public class FileCon {


//    private static final String path = "D:/psychic";//服务器路径
//    private static final String excelFile = "/excels/";//相对路径

    private String path;
    private String excelFile;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(String excelFile) {
        this.excelFile = excelFile;
    }
}
