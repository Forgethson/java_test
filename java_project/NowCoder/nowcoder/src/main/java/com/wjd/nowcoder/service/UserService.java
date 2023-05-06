package com.wjd.nowcoder.service;

import com.wjd.nowcoder.dao.LoginTicketMapper;
import com.wjd.nowcoder.dao.UserMapper;
import com.wjd.nowcoder.entity.LoginTicket;
import com.wjd.nowcoder.entity.User;
import com.wjd.nowcoder.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Arg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService implements CommunityConstant {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;

    // 地址
    @Value("${community.path.domain}")
    private String domain;

    // 应用程序上下文
    @Value("${server.servlet.context-path}")
    private String contextPath;

//    @Autowired
//    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private HostHolder hostHolder;

    public User findUserById(int id) {
//        return userMapper.selectById(id);
        User user = getCache(id);
        if (user == null) {
            user = initCache(id);
        }
        return user;
    }

    // 注册，需要返回多种异常消息结果，使用map集合
    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        // User对象的空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        // 对关键数据验证
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空!");
            return map;
        }

        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已存在!");
            return map;
        }

        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被注册!");
            return map;
        }

        // 注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5)); // 随机加salt字符串
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt())); // 用加salt的覆盖原密码
        user.setType(0); // 普通用户
        user.setStatus(0); // 没有激活
        user.setActivationCode(CommunityUtil.generateUUID()); // 设置激活码
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000))); // 随机头像，%d占位符
        user.setCreateTime(new Date()); // 注册时间
        // 插入用户到数据库时，由于mybatis设置了自增主键，则会将id值返回给user对象的id属性，后面getId直接就可以获得自增主键的值
        userMapper.insertUser(user);

        // 激活邮件
        Context context = new Context();
        // 设置Thymeleaf的上下文变量
        context.setVariable("email", user.getEmail());
        // RESTFUL：http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        // Thymeleaf渲染结果
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "NowCoder：激活账号", content);

        return map;
    }

    // 返回激活后的状态
    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if (user.getStatus() == 1) {
            // 重复激活
            return ACTIVATION_REPEAT;
        } else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);
            clearCache(userId);
            // 激活成功
            return ACTIVATION_SUCCESS;
        } else {
            // 激活失败
            return ACTIVATION_FAILURE;
        }
    }

    // 登录
    public Map<String, Object> login(String username, String password, int expiredSeconds) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (StringUtils.isBlank(username)) {
            map.put("usernameMsg", "账号不能为空!");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("passwordMsg", "密码不能为空!");
            return map;
        }
        // 验证账号
        User user = userMapper.selectByName(username);
        if (user == null) {
            map.put("usernameMsg", "该账号不存在!");
            return map;
        }
        // 验证状态
        if (user.getStatus() == 0) {
            map.put("usernameMsg", "该账号未激活!");
            return map;
        }
        // 验证密码
        password = CommunityUtil.md5(password + user.getSalt());
        if (!user.getPassword().equals(password)) {
            map.put("passwordMsg", "密码不正确!");
            return map;
        }

        // 生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(CommunityUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000L));
        //------------是否要先检查数据库中有无登录凭证？失效？答，目前在拦截器中实现，如果有有效的登录凭证，会直接将user放入到请求域，即变为登录状态
        // 此时，只有手动退出，或者等凭证过期之后，变为未登录状态。
//        loginTicketMapper.insertLoginTicket(loginTicket);
        // Redis优化：
        String redisKey = RedisKeyUtil.getTicketKey(loginTicket.getTicket());
        redisTemplate.opsForValue().set(redisKey, loginTicket); // 会自动序列化为JSON字符串

        // map中添加凭证字符串
        map.put("ticket", loginTicket.getTicket());
        return map;
    }

    // 退出登录
    public void logout(String ticket) {
        // 更新凭证id的状态
//        loginTicketMapper.updateStatus(ticket, 1);
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        LoginTicket loginTicket = (LoginTicket) redisTemplate.opsForValue().get(redisKey);
        loginTicket.setStatus(1);
        redisTemplate.opsForValue().set(redisKey, loginTicket);
    }

    // 查找登录凭证pojo
    public LoginTicket findLoginTicket(String ticket) {
//        return loginTicketMapper.selectByTicket(ticket);
        String redisKey = RedisKeyUtil.getTicketKey(ticket);
        return (LoginTicket) redisTemplate.opsForValue().get(redisKey);
    }

    // 更新用户头像
    public int updateHeader(int userId, String headerUrl) {
        // 更新给定id的用户头像
//        return userMapper.updateHeader(userId, headerUrl);
        int rows = userMapper.updateHeader(userId, headerUrl);
        clearCache(userId);
        return rows;
    }

    // 修改密码
    public Map<String, Object> changePwd(String oldPwd, String newPwd1, String newPwd2) {
        Map<String, Object> map = new HashMap<>();
        // 空值处理
        if (oldPwd == null) {
            map.put("oldPwdMsg", "原密码不能为空!");
            return map;
        }
        if (newPwd1 == null) {
            map.put("newPwd1Msg", "新密码不能为空!");
            return map;
        }
        if (newPwd2 == null) {
            map.put("newPwd2Msg", "新密码不能为空!");
            return map;
        }

        // 验证原密码
        User user = hostHolder.getUser();
        oldPwd = CommunityUtil.md5(oldPwd + user.getSalt());
        if (!user.getPassword().equals(oldPwd)) {
            map.put("oldPwdMsg", "原密码不正确!");
            return map;
        }

        // 验证新密码1,2是否一致
        if (!newPwd1.equals(newPwd2)) {
            map.put("newPwd2Msg", "两次输入的密码不一致!");
            return map;
        }
        // 更新密码和salt
        String salt = CommunityUtil.generateUUID().substring(0, 5);
        userMapper.updateSalt(user.getId(), salt);
        newPwd1 = CommunityUtil.md5(newPwd1 + salt);
        userMapper.updatePassword(user.getId(), newPwd1);
        return map;
    }

    // 通过用户名查询User
    public User findUserByName(String username) {
        return userMapper.selectByName(username);
    }

    // 1.优先从缓存中取值
    private User getCache(int userId) {
        // 自动序列化为JSON字符串
        String redisKey = RedisKeyUtil.getUserKey(userId);
        return (User) redisTemplate.opsForValue().get(redisKey);
    }

    // 2.取不到时，从MySQL中初始化缓存数据
    private User initCache(int userId) {
        User user = userMapper.selectById(userId);
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.opsForValue().set(redisKey, user, 3600, TimeUnit.SECONDS); // 过期时间1h
        return user;
    }

    // 3.数据变更时清除对应的缓存数据
    private void clearCache(int userId) {
        String redisKey = RedisKeyUtil.getUserKey(userId);
        redisTemplate.delete(redisKey);
    }

}

