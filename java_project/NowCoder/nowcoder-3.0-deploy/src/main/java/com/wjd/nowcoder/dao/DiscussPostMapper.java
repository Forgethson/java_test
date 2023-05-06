package com.wjd.nowcoder.dao;

import com.wjd.nowcoder.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit, int orderMode);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    // 根据id查询帖子
    DiscussPost selectDiscussPostById(int id);

    // 更新帖子的评论数量
    int updateCommentCount(int id, int commentCount);

    // 更新帖子类型（0-普通; 1-置顶;）
    int updateType(int id, int type);

    // 更新帖子状态（0-正常; 1-精华; 2-拉黑;）
    int updateStatus(int id, int status);

    // 更新帖子分数
    int updateScore(int id, double score);
}
