package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.Admin;
import com.lzqs.zhangyushu.dao.AdminMapper;
import com.lzqs.zhangyushu.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    AdminMapper adminMapper;


    @Override
    public ResultInfo addAdmin(String mobile, String password, String description, String account, String name) {

        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("account", account));
        Admin admin1 = adminMapper.selectOne(new QueryWrapper<Admin>().eq("mobile", mobile));
        if (admin!= null || admin1!=null){
            return ResultInfo.failWithMsg("账号或手机号已经存在, 直接登录");
        }
        Admin newAdmin= new Admin();
        newAdmin.setAccount(account);
        newAdmin.setMobile(mobile);
        newAdmin.setName(name);
        newAdmin.setPassword(password);
        newAdmin.setDescription(description);
        newAdmin.setAddTime(LocalDateTime.now());
        newAdmin.setUpdateTime(LocalDateTime.now());
        int a = adminMapper.insert(newAdmin);
        if (a>0){
            return ResultInfo.success();
        }
        return  ResultInfo.failWithMsg("添加失败");
    }

    @Override
    public ResultInfo login(String account, String mobile, String encode_password) {
        List<Admin> admins = account!=null? adminMapper.selectList(new QueryWrapper<Admin>().eq("account", account))
                :adminMapper.selectList(new QueryWrapper<Admin>().eq("mobile", mobile)) ;
        if (admins.isEmpty()){
            return ResultInfo.failWithMsg("账号不存在");
        }
        Admin admin = admins.get(0);
        if (!admin.getPassword().equals(encode_password)){
            return ResultInfo.failWithMsg("密码不正确");
        }
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateById(admin);

        return ResultInfo.success().add(admin);
    }

    @Override
    public ResultInfo editAdmin(Long adminId, String mobile, String password, String description, String account, String name) {
        Admin admin = adminMapper.selectById(adminId);
        admin.setIsDelete(1);
        adminMapper.updateById(admin);
        Admin admin2 = adminMapper.selectOne(new QueryWrapper<Admin>().eq("mobile", mobile).ne("is_delete", 1));
        Admin admin1 = adminMapper.selectOne(new QueryWrapper<Admin>().eq("account", account).ne("is_delete", 1));
        if (admin2!=null || admin1!=null){
            admin.setIsDelete(0);
            adminMapper.updateById(admin);
            return ResultInfo.failWithMsg("该手机号或账号已经存在");
        }
        admin.setName(name);
        admin.setMobile(mobile);
        admin.setPassword(password);
        admin.setAccount(account);
        admin.setDescription(description);
        admin.setUpdateTime(LocalDateTime.now());
        admin.setIsDelete(0);
        adminMapper.updateById(admin);

        return ResultInfo.success().add(admin);
    }
}
