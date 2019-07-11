package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.entity.SampleReels;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 作品集表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */

@Service
public interface SampleReelsService extends IService<SampleReels> {


    //根据用户id查看他的绘馆
    List<Map<String,Object>> listSampleReels(Long userId);

    //根据作品集id查看所有作品
    List<Map<String,Object>>  listProduction(Long sampleReelsId);

    //根据作品id查看作品详情
    Map<String,Object>  getProduction(Long productionId);

    SampleReels saveSample(HttpServletRequest request, Long userId, String studentName, Integer status, String sampleReelsName, String description);

    List getGallery(Long organizationId);

    Map<String, Object> getNumber(Long organizationId);

    void setView(Long sampleReelsId); //djdjsds

}
