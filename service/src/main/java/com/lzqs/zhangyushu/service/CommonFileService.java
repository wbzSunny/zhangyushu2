package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.entity.CommonFile;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface CommonFileService extends IService<CommonFile> {


    /**
     * 上传文件
     *
     * @param request
     */
    List<CommonFile> uploadFile(HttpServletRequest request);

    /**
     * 保存文件信息
     *
     * @return
     */
    CommonFile saveCommonFile(String fileName, String filePath, Long fileSize);

}
