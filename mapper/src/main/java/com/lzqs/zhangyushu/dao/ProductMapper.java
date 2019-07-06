package com.lzqs.zhangyushu.dao;

import com.lzqs.zhangyushu.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 作品表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Component
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
