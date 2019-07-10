package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.entity.Clew;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 线索表 （潜在用户） 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface ClewService extends IService<Clew> {

    Map<String, Object> getNumber(Long organizationId, long view);
}
