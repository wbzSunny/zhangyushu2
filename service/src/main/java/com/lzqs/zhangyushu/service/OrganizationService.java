package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface OrganizationService extends IService<Organization> {
    // 创建机构
    ResultInfo addOrganization(HttpServletRequest request, String userId, String linkMan, String mobile, String organizationName, String organizationdesc);

}
