package com.wjd.nowcoder.controller;

import com.wjd.nowcoder.annotation.LoginRequired;
import com.wjd.nowcoder.entity.Comment;
import com.wjd.nowcoder.entity.DiscussPost;
import com.wjd.nowcoder.entity.Event;
import com.wjd.nowcoder.event.EventProducer;
import com.wjd.nowcoder.service.CommentService;
import com.wjd.nowcoder.service.DiscussPostService;
import com.wjd.nowcoder.util.CommunityConstant;
import com.wjd.nowcoder.util.HostHolder;
import com.wjd.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController implements CommunityConstant {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private RedisTemplate redisTemplate;

    // 在帖子详情页面中，发布评论
//    @LoginRequired // 添加评论要求登录
    @PostMapping(path = "/add/{discussPostId}")
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        // 通过本地线程，获取当前用户的id
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        // 触发评论事件，产生系统通知
        Event event = new Event()
                .setTopic(TOPIC_COMMENT)
                .setUserId(hostHolder.getUser().getId()) // 触发用户：当前用户
                .setEntityType(comment.getEntityType())
                .setEntityId(comment.getEntityId())
                .setData("postId", discussPostId); // 前面没有给Event设置所在的帖子id，需要放到data中
        // 如果该评论回复的是帖子，应查找出相应帖子的信息（被评论实体）
        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            System.out.println(comment.getEntityId() == discussPostId);
            DiscussPost target = discussPostService.findDiscussPostById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
            // 如果该评论回复的是评论，应查找出相应评论的信息（被评论实体）
        } else if (comment.getEntityType() == ENTITY_TYPE_COMMENT) {
            Comment target = commentService.findCommentById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        }
        // 发布消息
        eventProducer.fireEvent(event);

        // 触发发帖事件，加入ES服务器
        if (comment.getEntityType() == ENTITY_TYPE_POST) {
            event = new Event()
                    .setTopic(TOPIC_PUBLISH)
                    .setUserId(comment.getUserId())
                    .setEntityType(ENTITY_TYPE_POST)
                    .setEntityId(discussPostId);
            eventProducer.fireEvent(event);
            // 计算帖子分数
            String redisKey = RedisKeyUtil.getPostScoreKey();
            redisTemplate.opsForSet().add(redisKey, discussPostId);
        }

        return "redirect:/discuss/detail/" + discussPostId;
    }

}
