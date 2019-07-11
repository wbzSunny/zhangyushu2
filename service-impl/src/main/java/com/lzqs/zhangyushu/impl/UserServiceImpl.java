package com.lzqs.zhangyushu.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzqs.zhangyushu.common.ResultInfo;
import com.lzqs.zhangyushu.constant.UserConstant;
import com.lzqs.zhangyushu.dao.ProductMapper;
import com.lzqs.zhangyushu.dao.SampleReelsMapper;
import com.lzqs.zhangyushu.entity.Product;
import com.lzqs.zhangyushu.entity.SampleReels;
import com.lzqs.zhangyushu.entity.User;
import com.lzqs.zhangyushu.dao.UserMapper;
import com.lzqs.zhangyushu.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private UserMapper userMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private SampleReelsMapper sampleReelsMapper;
    /**
     * 登录时根据openID 获取用户信息 不存在 就添加用户
     * @param openid
     * @return
     */
    @Override
    public User queryUserByOpenId(String openid) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id",openid));
        if (user == null){
            user = new User();
            user.setAddTime(LocalDateTime.now());
            user.setOpenId(openid);
            user.setStatus(UserConstant.COMMON);
            userMapper.insert(user);
        }

        return user;
    }

    /**
     * 根据前端用户拉取
     * @param userId
     * @param userHead
     * @param nickName
     * @param gender
     * @return
     */
    @Override
    public ResultInfo updateByInfo(Long userId, String userHead, String nickName, Integer gender) {
        User user =userMapper.selectById(userId);
        user.setEditTime(LocalDateTime.now());
        user.setGender(gender);
        user.setUserHead(userHead);
        user.setUserName(nickName);
        userMapper.updateById(user);
        return ResultInfo.success();
    }

    @Override
    public ResultInfo userDetial(String userId) {
        User user = userMapper.selectById(userId);

        List<SampleReels> sampleReelsList = sampleReelsMapper.selectList(new QueryWrapper<SampleReels>().eq("user_id",userId));
        int likeNum = 0;
        int commotNum = 0;
        int productNum = 0;
        int visitNum  = 0;

        if (sampleReelsList != null && sampleReelsList.size() != 0){
            for (SampleReels sampleReels : sampleReelsList){
                likeNum+= sampleReels.getLikeNum();
                commotNum+= sampleReels.getCommentNum();
                visitNum  += sampleReels.getViewNum();
                List<Product> productList = productMapper.selectList(new QueryWrapper<Product>().eq("sample_reels_id",sampleReels.getSampleReelsId()));
                productNum+=productList.size();
            }
        }


        Map<String,Object> map = new HashMap<>();
        map.put("用户名",user.getUserName());
        map.put("作品数",productNum);
        map.put("作品集数",sampleReelsList.size());
        map.put("点赞数" ,likeNum);
        map.put("评论数" ,commotNum);
        map.put("访问数" ,visitNum);
        return ResultInfo.success().add(map);
    }
    //邀请人id 不是空 判断邀请人是不是商户 是商户 将当前登录的 用户 变更用户状态是商户学员

    @Override
    public User editUserStatus(String openid, Long inviteId) {
        User inviteUser = userMapper.selectById(inviteId);
        if (inviteUser.getStatus() == UserConstant.VERDOR){
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id",openid));
            if (user == null){
                user = new User();
                user.setAddTime(LocalDateTime.now());
                user.setOpenId(openid);
                user.setStatus(UserConstant.VERDOR_STUDENT);
                userMapper.insert(user);
            }else {
                user.setStatus(UserConstant.VERDOR_STUDENT);
                userMapper.updateById(user);
            }
            return user;

        }else {
           return queryUserByOpenId(openid);
        }

    }
    @Override
    public List<User> getList(Long organizationId, int status) {
        List<User> studentList = userMapper.selectList(new QueryWrapper<User>().eq("binding_id", organizationId).eq("status", status));
        return studentList;
    }
}
