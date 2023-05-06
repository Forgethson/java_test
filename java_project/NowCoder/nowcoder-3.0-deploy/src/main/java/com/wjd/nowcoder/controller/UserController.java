package com.wjd.nowcoder.controller;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.wjd.nowcoder.annotation.LoginRequired;
import com.wjd.nowcoder.entity.User;
import com.wjd.nowcoder.service.FollowService;
import com.wjd.nowcoder.service.LikeService;
import com.wjd.nowcoder.service.UserService;
import com.wjd.nowcoder.util.CommunityConstant;
import com.wjd.nowcoder.util.CommunityUtil;
import com.wjd.nowcoder.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${community.path.upload}")
    private String uploadPath;

    // 域名
    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    // 用于获取当前用户的User对象
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FollowService followService;

    @Value("${qiniu.key.access}")
    private String accessKey;

    @Value("${qiniu.key.secret}")
    private String secretKey;

    @Value("${qiniu.bucket.header.name}")
    private String headerBucketName;

    @Value("${quniu.bucket.header.url}")
    private String headerBucketUrl;

    // 进入设置页面
    @LoginRequired
    @GetMapping(path = "/setting")
    public String getSettingPage(Model model) {
        // 上传文件名称（需要文件名称来生成凭证，因此需要提前设置）
        String fileName = CommunityUtil.generateUUID();
        // 设置响应信息（即希望七牛云在成功的时候返回JSON字符串{"code":0}，其他值为失败）
        StringMap policy = new StringMap();
        policy.put("returnBody", CommunityUtil.getJSONString(0));
        // 生成上传凭证，后面依据凭证以及文件名上传到七牛云服务器
        Auth auth = Auth.create(accessKey, secretKey);
        String uploadToken = auth.uploadToken(headerBucketName, fileName, 3600, policy);

        model.addAttribute("uploadToken", uploadToken);
        model.addAttribute("fileName", fileName);

        return "/site/setting";
    }

    /*//    @LoginRequired
    // 进入设置页面
    @GetMapping(path = "/setting")
    public String getSettingPage() {
        return "/site/setting";
    }*/

    // 上传服务器成功，单独写一个处理异步请求的方法，在数据库中更新头像路径
    @PostMapping(path = "/header/url")
    @ResponseBody
    public String updateHeaderUrl(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return CommunityUtil.getJSONString(1, "文件名不能为空!");
        }

        // http://rs0hjxid9.hb-bkt.clouddn.com/b779466a603545a28f1c0ef344febe89
        String url = headerBucketUrl + "/" + fileName;
        userService.updateHeader(hostHolder.getUser().getId(), url);

        return CommunityUtil.getJSONString(0, "头像上传成功");
    }

    // 改用云服务器，废弃    //    @LoginRequired
    @Deprecated
    @PostMapping(path = "/upload")
    public String uploadHeader(MultipartFile headerImage, Model model) {
        if (headerImage == null) {
            model.addAttribute("error", "您还没有选择图片!");
            return "/site/setting";
        }

        // 原文件名
        String fileName = headerImage.getOriginalFilename();
        // 扩展名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)) {
            model.addAttribute("error", "文件的格式不正确!");
            return "/site/setting";
        }

        // 生成随机文件名
        fileName = CommunityUtil.generateUUID() + suffix;
        // 确定文件存放的路径
        File dest = new File(uploadPath + "/" + fileName);
        try {
            // 存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败: " + e.getMessage());
            throw new RuntimeException("上传文件失败,服务器发生异常!", e);
        }

        // 更新当前用户的头像的路径(web访问路径)，自己定义为：
        // http://localhost:8080/community/user/header/xxx.png（该路径允许web外部直接访问）
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }


    @Deprecated// 改云服务器，废弃
    // 获取头像，拦截上面自定义的头像url，返回对应的存放在服务器上面的头像图片文件
    @GetMapping(path = "/header/{fileName}")
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        // 服务器存放对应文件的路径
        fileName = uploadPath + "/" + fileName;
        // 文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/" + suffix);
        // 该语法是try-with-resources，括号后try，我们在其中声明将使用的资源，并在try块中完成工作后，我将立即调用它们的.close()方法
        try (
                FileInputStream fis = new FileInputStream(fileName);
                OutputStream os = response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败: " + e.getMessage());
        }
    }

    @PostMapping(path = "/changePwd")
    public String changePwd(Model model, String oldPwd, String newPwd1, String newPwd2) {
        Map<String, Object> map = userService.changePwd(oldPwd, newPwd1, newPwd2);
        // 修改密码成功
        if (map == null || map.isEmpty()) {
            // 退出当前的登录状态
            return "redirect:/logout";
        } else {
            // 修改失败，则下面的属性设置只有一个不为null，转发到设置界面（属于同一个请求域）
            model.addAttribute("oldPwdMsg", map.get("oldPwdMsg"));
            model.addAttribute("newPwd1Msg", map.get("newPwd1Msg"));
            model.addAttribute("newPwd2Msg", map.get("newPwd2Msg"));
            return "/site/setting";
        }
    }

    // 个人主页
    @GetMapping(path = "/profile/{userId}")
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在!");
        }
        // 用户
        model.addAttribute("user", user);
        // 点赞数量
        int likeCount = likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount", likeCount);

        // 关注数量
        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
        model.addAttribute("followeeCount", followeeCount);
        // 粉丝数量
        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
        model.addAttribute("followerCount", followerCount);
        // 是否已关注
        boolean hasFollowed = false;
        if (hostHolder.getUser() != null) {
            hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
        }
        model.addAttribute("hasFollowed", hasFollowed);

        return "/site/profile";
    }

}
