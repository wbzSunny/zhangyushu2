package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.entity.Clew;
import com.lzqs.zhangyushu.dao.ClewMapper;
import com.lzqs.zhangyushu.service.ClewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 线索表 （潜在用户） 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class ClewServiceImpl extends ServiceImpl<ClewMapper, Clew> implements ClewService {
    @Resource
    ClewMapper clewMapper;

    @Override
    public Map<String, Object> getNumber(Long organizationId, long view) {
        Map<String, Object> info = new HashMap<>();
        QueryWrapper<Clew> clewQueryWrapper = new QueryWrapper<>();
        clewQueryWrapper.eq("organization_id", organizationId);
        info.put("allSize", clewMapper.selectMaps(clewQueryWrapper).size());
        clewQueryWrapper.eq("view_num", view);
        info.put("viewSize", clewMapper.selectMaps(clewQueryWrapper).size());
        return info;
    }
}
