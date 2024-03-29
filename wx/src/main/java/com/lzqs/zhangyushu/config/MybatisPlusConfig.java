package com.lzqs.zhangyushu.config;
 
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus分页插件<br>
 * 文档：http://mp.baomidou.com<br>
 */
@Configuration
@MapperScan("com.lzqs.acbsp.mapper")
public class MybatisPlusConfig {
 
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
 
 
}
