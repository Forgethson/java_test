package com.wjd.nowcoder.util;

public class RedisKeyUtil {

    private static final String SPLIT = ":";
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    private static final String PREFIX_USER_LIKE = "like:user";
    private static final String PREFIX_FOLLOWEE = "followee";
    private static final String PREFIX_FOLLOWER = "follower";
    private static final String PREFIX_KAPTCHA = "kaptcha";
    private static final String PREFIX_TICKET = "ticket";
    private static final String PREFIX_USER = "user";

    // 某个实体（帖子或者评论等）的赞
    // like:entity:entityType:entityId -> set(userId)，将点赞的用户id放到一个Set集合中（方便查找是哪个用户点过赞）
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    // 某个用户被点的（帖子或者评论等）赞
    // like:user:userId -> int，用普通的字符串存放即可，value的类型为：int
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    // 某个用户关注的实体（帖子或者评论等）通过entityType分割，表示关注的帖子集合、关注的用户集合等
    // followee:userId:entityType -> zset(entityId,now)，可排序集合存放，按照时间顺序排序
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    // 某个实体（帖子或者评论等）拥有的粉丝
    // follower:entityType:entityId -> zset(userId,now)，可排序集合存放，按照时间顺序排序
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    // 登录验证码：kaptcha:owner -> String
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    // 登录的凭证：ticket:ticketID -> String
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    // 用户：user:userId -> String
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

}
