package com.lzqs.zhangyushu.impl;

import com.lzqs.zhangyushu.entity.Product;
import com.lzqs.zhangyushu.dao.ProductMapper;
import com.lzqs.zhangyushu.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作品表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
