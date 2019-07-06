package com.lzqs.zhangyushu.impl;

import com.lzqs.zhangyushu.entity.Comment;
import com.lzqs.zhangyushu.dao.CommentMapper;
import com.lzqs.zhangyushu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论列表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2019-07-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
