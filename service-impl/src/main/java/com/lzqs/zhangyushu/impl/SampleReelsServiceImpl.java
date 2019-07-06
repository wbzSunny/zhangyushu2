package com.lzqs.zhangyushu.impl;

import com.lzqs.zhangyushu.dao.CommonFileMapper;
import com.lzqs.zhangyushu.dao.UserMapper;
import com.lzqs.zhangyushu.entity.CommonFile;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.service.CommonFileService;
import com.lzqs.zhangyushu.service.SampleReelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 作品集表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class SampleReelsServiceImpl extends ServiceImpl<SampleReelsMapper, SampleReels> implements SampleReelsService {
    @Resource
    CommonFileService commonFileService;
    @Resource
    SampleReelsMapper sampleReelsMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public SampleReels saveSample(HttpServletRequest request, Long userId, int status, String sampleReelsName, String description) {

        List<CommonFile> commonFiles = commonFileService.uploadFile(request);
        if (commonFiles.isEmpty()){
            return null;
        }
        CommonFile commonFile = commonFiles.get(0);
        SampleReels sampleReels = new SampleReels();
        sampleReels.setUserId(userId);
        sampleReels.setUserName(userMapper.selectById(userId).getUserName());
        sampleReels.setSampleReelsName(sampleReelsName);
        sampleReels.setSampleReelsCover(commonFile.getId());
        sampleReels.setSamoleReelsDesc(description);
        sampleReels.setCommentNum(0);
        sampleReels.setLikeNum(0);
        sampleReels.setViewNum(Long.valueOf(0));
        sampleReels.setCreateTime(LocalDateTime.now());
        sampleReels.setStatus(status);

        sampleReelsMapper.insert(sampleReels);
        return sampleReels;
    }
}
