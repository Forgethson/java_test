package com.wjd.nowcoder.controller;

import com.google.code.kaptcha.Producer;
import com.wjd.nowcoder.entity.User;
import com.wjd.nowcoder.service.UserService;
import com.wjd.nowcoder.util.CommunityConstant;
import com.wjd.nowcoder.util.CommunityUtil;
import com.wjd.nowcoder.util.RedisKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Producer kaptchaProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    // 应用程序上下文
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping(path = "/register")
    public String getRegisterPage() {
        return "/site/register";
    }

    @GetMapping(path = "/login")
    public String getLoginPage() {
        return "/site/login";
    }

    // 注册请求
    @PostMapping(path = "/register")
    public String register(Model model, User user) {
        // map作为返回异常消息结果的集合
        // 这里请求参数自动传到User中，同时将其放到请求域，name=user
        Map<String, Object> map = userService.register(user);
        if (map == null || map.isEmpty()) {
            model.addAttribute("msg", "注册成功,我们已经向您的邮箱发送了一封激活邮件,请尽快激活!");
            model.addAttribute("target", "/index");
            return "/site/operate-result";
        } else {
            // 注册失败，则下面的属性设置只有一个不为null，转发到注册界面（属于同一个请求域）
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            model.addAttribute("emailMsg", map.get("emailMsg"));
            return "/site/register";
        }
    }

    // 激活账户
    // http://localhost:8080/community/activation/101/code
    @RequestMapping(path = "/activation/{userId}/{code}", method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId") int userId, @PathVariable("code") String code) {
        int result = userService.activation(userId, code);
        if (result == ACTIVATION_SUCCESS) {
            model.addAttribute("msg", "激活成功,您的账号已经可以正常使用了!");
            model.addAttribute("target", "/login");
        } else if (result == ACTIVATION_REPEAT) {
            model.addAttribute("msg", "无效操作,该账号已经激活过了!");
            model.addAttribute("target", "/index");
        } else {
            model.addAttribute("msg", "激活失败,您提供的激活码不正确!");
            model.addAttribute("target", "/index");
        }
        return "/site/operate-result";
    }

    // 生成验证码
    @GetMapping(path = "/kaptcha")
    public void getKaptcha(HttpServletResponse response/*, HttpSession session*/) {
        // 生成验证码
        String text = kaptchaProducer.createText(); // 字符串
        BufferedImage image = kaptchaProducer.createImage(text); // 图片数据
//        // 将验证码存入session
//        session.setAttribute("kaptcha", text);

        // 随机生成验证码的归属（键的一部分：kaptcha:owner）
        String kaptchaOwner = CommunityUtil.generateUUID();
        // 将验证码的归属（part of key）放入Cookie中
        Cookie cookie = new Cookie("kaptchaOwner", kaptchaOwner);
        cookie.setMaxAge(60);
        cookie.setPath(contextPath);
        response.addCookie(cookie);
        // 将验证码存入Redis
        String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
        redisTemplate.opsForValue().set(redisKey, text, 60, TimeUnit.SECONDS);

        // 将图片输出给浏览器
        response.setContentType("image/png");
        try {
            OutputStream os = response.getOutputStream();
            ImageIO.write(image, "png", os);
        } catch (IOException e) {
            logger.error("响应验证码失败:" + e.getMessage());
        }
    }

    // 登录社区，rememberme表示是否勾上“记住我”
    @PostMapping(path = "/login")
    public String login(String username, String password, String code, boolean rememberme, Model model/*, HttpSession session*/,
                        HttpServletResponse response, @CookieValue("kaptchaOwner") String kaptchaOwner) {
//        // 检查验证码
//        String kaptcha = (String) session.getAttribute("kaptcha");
//        if (StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code)) {
//            model.addAttribute("codeMsg", "请输入验证码!");
//            return "/site/login"; // 转发
//        }
//        if (!kaptcha.equalsIgnoreCase(code)) {
//            model.addAttribute("codeMsg", "验证码不正确!");
//            return "/site/login"; // 转发
//        }

        // 检查验证码
        String kaptcha = null;
        if (StringUtils.isNotBlank(kaptchaOwner)) {
            String redisKey = RedisKeyUtil.getKaptchaKey(kaptchaOwner);
            kaptcha = (String) redisTemplate.opsForValue().get(redisKey);
        }

        if (StringUtils.isBlank(kaptcha) || StringUtils.isBlank(code) || !kaptcha.equalsIgnoreCase(code)) {
            model.addAttribute("codeMsg", "验证码不正确!");
            return "/site/login";
        }
        // 检查账号,密码
        int expiredSeconds = rememberme ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;
        Map<String, Object> map = userService.login(username, password, expiredSeconds);
        // 如果得到了登录凭证字符串
        if (map.containsKey("ticket")) {
            // 将登录凭证放入Cookie中响应，这里map中的val编译类型是Object，但是运行类型是String。
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
            cookie.setPath(contextPath);
            cookie.setMaxAge(expiredSeconds);
            response.addCookie(cookie);
            return "redirect:/index";
        } else {
            model.addAttribute("usernameMsg", map.get("usernameMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "/site/login";
        }
    }

    // 退出
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/login";
    }

}
