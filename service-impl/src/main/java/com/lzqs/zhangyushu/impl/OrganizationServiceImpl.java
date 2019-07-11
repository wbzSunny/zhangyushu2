package com.lzqs.zhangyushu.impl;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.constant.OrganizationConstant;
import com.lzqs.zhangyushu.entity.CommonFile;
import com.lzqs.zhangyushu.entity.Organization;
import com.lzqs.zhangyushu.dao.OrganizationMapper;
import com.lzqs.zhangyushu.service.CommonFileService;
import com.lzqs.zhangyushu.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Resource
    private  OrganizationMapper organizationMapper;
    @Resource
    private CommonFileService commonFileService;

    /**
     * 创建机构
     * @param userId
     * @param linkMan
     * @param mobile
     * @param organizationName
     * @param organizationdesc
     * @return
     */
    @Override
    public ResultInfo addOrganization(HttpServletRequest request, String userId, String linkMan,  String mobile, String organizationName, String organizationdesc) {

        List<CommonFile> commonFiles = commonFileService.uploadFile(request);
        if (commonFiles.isEmpty()){
            return null;
        }
        CommonFile commonFile = commonFiles.get(0);

        Organization organization = new Organization();
        organization.setCreatTime(LocalDateTime.now());
        organization.setOrganizationDesc(organizationdesc);
        organization.setOrganizationLogo(commonFile.getId());
        organization.setOrganizationName(organizationName);
        organization.setUserPhone(mobile);
        organization.setUserId(Long.valueOf(userId));
        organization.setLinkMan(linkMan);
        organization.setStatus(OrganizationConstant.AUDITING);
        organizationMapper.insert(organization);

        return ResultInfo.success().add(organization);
    }
}
