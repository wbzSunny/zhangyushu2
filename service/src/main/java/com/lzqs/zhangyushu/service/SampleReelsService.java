package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.entity.SampleReels;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 作品集表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface SampleReelsService extends IService<SampleReels> {

    SampleReels saveSample(HttpServletRequest request, Long userId, int status, String sampleReelsName, String description);
}
