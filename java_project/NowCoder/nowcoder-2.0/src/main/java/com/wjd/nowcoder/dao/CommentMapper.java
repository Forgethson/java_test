package com.wjd.nowcoder.dao;

import com.wjd.nowcoder.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 根据实体类型，及其id来查询，以及分页条件查询评论集合
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    // 查询评论的总条目数（用于分页）
    int selectCountByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);
}
