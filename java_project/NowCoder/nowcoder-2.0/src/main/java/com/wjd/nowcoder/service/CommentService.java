package com.wjd.nowcoder.service;

import com.wjd.nowcoder.dao.CommentMapper;
import com.wjd.nowcoder.entity.Comment;
import com.wjd.nowcoder.util.CommunityConstant;
import com.wjd.nowcoder.util.CommunityUtil;
import com.wjd.nowcoder.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;


@Service
public class CommentService implements CommunityConstant {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;

    // 根据实体类型，及其id来查询，以及分页条件查询评论集合
    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    // 查询评论的总条目数
    public int findCommentCount(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);
    }

    // 声明式事务（这里一个service的方法中包含了两次DML操作，因此将其封装为一个事务，利用事务的原子性，保证了添加评论
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int addComment(Comment comment) {
        if (comment == null) {
            // 这样更新评论事务就回滚了
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 添加评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent())); // 正确显示转义字符
        comment.setContent(sensitiveFilter.filter(comment.getContent())); // 过滤敏感词
        int rows = commentMapper.insertComment(comment);

        // 如果comment对象的实体类型为：帖子，则说明该comment是评论，需要更新帖子下面的评论数量，否则是评论下面的回复
        // discuss_post表中有一个字段用来表示该帖子的评论数量，需要同步更改。但是对于comment表，则无此对象，因此：
        // 在查询的时候需要额外统计评论的回复数目（commentService.findCommentCount）
        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            // 获得当前comment表中的最新的帖子评论数量（重新查询一遍）
            int count = commentMapper.selectCountByEntity(ENTITY_TYPE_POST, comment.getEntityId());
            // 在post表中更新
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }

        return rows;
    }

    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }

}
