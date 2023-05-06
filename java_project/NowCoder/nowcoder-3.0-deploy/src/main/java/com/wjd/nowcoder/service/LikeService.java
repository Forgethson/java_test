package com.wjd.nowcoder.service;

import com.wjd.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    // 点赞，输入：点赞的用户，实体类型+id，实体作者用户id
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // key：某一个实体
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                // key：某一个用户
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);

                // 查询操作最好放在事务过程之外，因为在Redis提交之前，操作都在队列中，并不会执行
                // 事先查询，判断该用户是否已经点过赞
                boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                // 开启事务
                operations.multi();

                if (isMember) { // 如果是，则取消点赞
                    operations.opsForSet().remove(entityLikeKey, userId);
                    // 该实体作者用户的赞-1（如果为空，新增值为-1）
                    operations.opsForValue().decrement(userLikeKey);
                } else { // 如果否，则点赞
                    operations.opsForSet().add(entityLikeKey, userId);
                    // 该实体作者用户的赞+1（如果为空，新增值为1）
                    operations.opsForValue().increment(userLikeKey);
                }

                return operations.exec();
            }
        });
    }

    // 查询某实体点赞的数量（如果没有对应的key就返回0）
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId); // 得到键名
        return redisTemplate.opsForSet().size(entityLikeKey); // 查询数量
    }

    // 查询某人对某实体的点赞状态，返回1表示已点赞，0表示未点赞
    public int findEntityLikeStatus(int userId, int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    // 查询某个用户获得的赞的数量
    public int findUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count.intValue();
    }

}
