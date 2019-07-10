package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.dao.UserMapper;
import com.lzqs.zhangyushu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<User> getList(Long organizationId, int status) {
        List<User> studentList = userMapper.selectList(new QueryWrapper<User>().eq("binding_id", organizationId).eq("status", status));
        return studentList;
    }
}
