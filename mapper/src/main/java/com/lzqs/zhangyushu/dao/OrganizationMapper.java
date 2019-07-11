package com.lzqs.zhangyushu.dao;

import com.lzqs.zhangyushu.entity.Organization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-07-11
 */
@Component
@Mapper
public interface OrganizationMapper extends BaseMapper<Organization> {

}
