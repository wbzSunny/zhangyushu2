package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-11
 */
public interface AdminService extends IService<Admin> {

    ResultInfo addAdmin(String mobile, String password, String description, String account, String name);

    ResultInfo login(String account, String mobile, String encode_password);

    ResultInfo editAdmin(Long adminId, String mobile, String password, String description, String account, String name);
}
