package com.wjd.nowcoder.controller;

import com.wjd.nowcoder.entity.DiscussPost;
import com.wjd.nowcoder.entity.Page;
import com.wjd.nowcoder.service.ElasticsearchService;
import com.wjd.nowcoder.service.LikeService;
import com.wjd.nowcoder.service.UserService;
import com.wjd.nowcoder.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController implements CommunityConstant {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    // search?keyword=xxx
    @GetMapping(path = "/search")
    public String search(String keyword, Page page, Model model) {
        // 搜索帖子
        Object[] searchResult = elasticsearchService.searchDiscussPost(keyword, page.getCurrent() - 1, page.getLimit());
        List<DiscussPost> list = (List<DiscussPost>) searchResult[0];
        long value = (long) searchResult[1];

        // 聚合数据
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (searchResult != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                // 帖子
                map.put("post", post);
                // 作者
                map.put("user", userService.findUserById(post.getUserId()));
                // 点赞数量
                map.put("likeCount", likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId()));

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("keyword", keyword);

        // 分页信息
        page.setPath("/search?keyword=" + keyword);
//        page.setRows(searchResult == null ? 0 : (int) value);
        page.setRows((int) value);

        return "/site/search";
    }

}
