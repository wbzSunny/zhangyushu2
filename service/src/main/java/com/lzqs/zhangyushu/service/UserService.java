package com.lzqs.zhangyushu.service;

import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
public interface UserService extends IService<User> {
    //登录时根据openID 获取用户信息 不存在 就添加用户
    User queryUserByOpenId(String openid);
    // 根据前台微信拉取用户信息更新用户信息
    ResultInfo updateByInfo(Long userId, String userHead, String nickName, Integer gender);
    // 获取用户详细信息
    ResultInfo userDetial(String userId);
    //邀请人id 不是空 判断邀请人是不是商户 是商户 将当前登录的 用户 变更用户状态是商户学员
    User editUserStatus(String openid, Long inviteId);

    List<User> getList(Long organizationId, int status);
}
