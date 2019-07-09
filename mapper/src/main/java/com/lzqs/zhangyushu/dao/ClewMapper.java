package com.lzqs.zhangyushu.dao;

import com.lzqs.zhangyushu.entity.Clew;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 线索表 （潜在用户） Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-07-09
 */
@Component
@Mapper
public interface ClewMapper extends BaseMapper<Clew> {

}
