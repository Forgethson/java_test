package com.wjd.nowcoder.controller;

import com.wjd.nowcoder.entity.*;
import com.wjd.nowcoder.event.EventProducer;
import com.wjd.nowcoder.service.CommentService;
import com.wjd.nowcoder.service.DiscussPostService;
import com.wjd.nowcoder.service.LikeService;
import com.wjd.nowcoder.service.UserService;
import com.wjd.nowcoder.util.CommunityConstant;
import com.wjd.nowcoder.util.CommunityUtil;
import com.wjd.nowcoder.util.HostHolder;
import com.wjd.nowcoder.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private EventProducer eventProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    // 发布帖子
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            // 给浏览器返回JSON格式的提示
            return CommunityUtil.getJSONString(403, "你还没有登录哦!");
        }

        // 构造帖子pojo对象
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 触发发帖事件
        Event event = new Event()
                .setTopic(TOPIC_PUBLISH)
                .setUserId(user.getId())
                .setEntityType(ENTITY_TYPE_POST)
                .setEntityId(post.getId());
        eventProducer.fireEvent(event);

        // 计算帖子分数
        String redisKey = RedisKeyUtil.getPostScoreKey();
        redisTemplate.opsForSet().add(redisKey, post.getId());

        // 报错的情况,将来统一处理.
        return CommunityUtil.getJSONString(0, "发布成功!");
    }

    // 帖子详情
    @GetMapping(path = "/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        // 根据id获得帖子pojo对象
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post", post);
        // 获得帖子对应的作者User对象
        User user = userService.findUserById(post.getUserId());
        model.addAttribute("user", user);
        // 点赞数量
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPostId);
        model.addAttribute("likeCount", likeCount);
        // 点赞状态，这里先判断本地线程是否为空（如果没有登录）
        int likeStatus = hostHolder.getUser() == null ? 0 : likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, discussPostId);

        // 设置评论分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail/" + discussPostId);
        page.setRows(post.getCommentCount());

        // 下面的提法说明：评论: 给帖子的评论；回复: 给评论的评论
        // 评论列表
        List<Comment> commentList = commentService.findCommentsByEntity(ENTITY_TYPE_POST, post.getId(), page.getOffset(), page.getLimit());
        // 评论VO（View Object，显示对象）列表
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) { // 对每一条评论：
                // 评论VO，将每一条评论中的，comment对象，作者User对象，以及后面的回复列表，放入map集合
                Map<String, Object> commentVo = new HashMap<>();
                commentVo.put("comment", comment); // 评论
                commentVo.put("user", userService.findUserById(comment.getUserId())); // 作者
                // 点赞数量
                likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("likeCount", likeCount);
                // 点赞状态
                likeStatus = hostHolder.getUser() == null ? 0 : likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("likeStatus", likeStatus);

                // 查询当前评论的回复列表（这里不需要分页，0,Integer.MAX_VALUE表示当前列表是有多少查多少）
                List<Comment> replyList = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                // 回复VO列表
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) { // 对每一条回复：
                        Map<String, Object> replyVo = new HashMap<>();
                        replyVo.put("reply", reply);  // 回复
                        replyVo.put("user", userService.findUserById(reply.getUserId())); // 作者
                        // target为0表示不是对用户的回复，而是对评论的回复
                        User target = reply.getTargetId() == 0 ? null : userService.findUserById(reply.getTargetId()); // 回复目标
                        replyVo.put("target", target);
                        // 点赞数量
                        likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, reply.getId());
                        replyVo.put("likeCount", likeCount);
                        // 点赞状态
                        likeStatus = hostHolder.getUser() == null ? 0 : likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_COMMENT, reply.getId());
                        replyVo.put("likeStatus", likeStatus);

                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys", replyVoList); // 回复列表

                // 回复数量
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("replyCount", replyCount);

                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comments", commentVoList);
        return "/site/discuss-detail";
    }

    // 置顶帖子
    @PostMapping(path = "/top")
    @ResponseBody
    public String setTop(int id) {
        String msg = "内部错误";
        int code = -1;
        DiscussPost discussPost = discussPostService.findDiscussPostById(id);
        // 若当前帖子类型为0，未置顶
        if (discussPost.getType() == 0) {
            discussPostService.updateType(id, 1);
            msg = "已置顶该帖子";
            code = 0;
        } else if (discussPost.getType() == 1) {
            // 若当前帖子类型为1，已经置顶
            discussPostService.updateType(id, 0);
            msg = "已取消置顶该帖子";
            code = 1;
        }

        // 触发发帖事件：kafka->ES服务器
        Event event = new Event()
                .setTopic(TOPIC_PUBLISH)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(ENTITY_TYPE_POST)
                .setEntityId(id);
        eventProducer.fireEvent(event);


        return CommunityUtil.getJSONString(code, msg);
    }

    // 加精帖子
    @PostMapping(path = "/wonderful")
    @ResponseBody
    public String setWonderful(int id) {
        String msg = "内部错误";
        int code = -1;
        DiscussPost discussPost = discussPostService.findDiscussPostById(id);
        // 若当前帖子状态为1，未加精
        if (discussPost.getStatus() == 0) {
            discussPostService.updateStatus(id, 1);
            msg = "已加精该帖子";
            code = 0;
        } else if (discussPost.getStatus() == 1) {
            // 若当前帖子状态为1，已经加精
            discussPostService.updateStatus(id, 0);
            msg = "已取消加精该帖子";
            code = 1;
        }

        // 触发发帖事件：kafka->ES服务器
        Event event = new Event()
                .setTopic(TOPIC_PUBLISH)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(ENTITY_TYPE_POST)
                .setEntityId(id);
        eventProducer.fireEvent(event);

        // 计算帖子分数
        String redisKey = RedisKeyUtil.getPostScoreKey();
        redisTemplate.opsForSet().add(redisKey, id);

        return CommunityUtil.getJSONString(code, msg);
    }

    // 删除帖子
    @PostMapping(path = "/delete")
    @ResponseBody
    public String setDelete(int id) {
        discussPostService.updateStatus(id, 2);

        // 触发删帖事件：kafka->ES服务器
        Event event = new Event()
                .setTopic(TOPIC_DELETE)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(ENTITY_TYPE_POST)
                .setEntityId(id);
        eventProducer.fireEvent(event);

        return CommunityUtil.getJSONString(0, "删除成功，2秒后自动跳转到主页");
    }
}
