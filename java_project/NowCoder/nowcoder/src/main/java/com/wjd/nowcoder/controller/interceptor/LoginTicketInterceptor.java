package com.wjd.nowcoder.controller.interceptor;

import com.wjd.nowcoder.dao.LoginTicketMapper;
import com.wjd.nowcoder.entity.LoginTicket;
import com.wjd.nowcoder.entity.User;
import com.wjd.nowcoder.service.UserService;
import com.wjd.nowcoder.util.CookieUtil;
import com.wjd.nowcoder.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从cookie中获取凭证字符串
        String ticket = CookieUtil.getValue(request, "ticket");
        if (ticket != null) {
            // 根据ticket，查询凭证pojo对象
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            // 检查凭证是否有效（不为null，有效，过期时间在当前时间之后）
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                // 根据凭证，查询用户pojo对象
                User user = userService.findUserById(loginTicket.getUserId());
                // 在本次请求中持有用户，同时线程隔离
                hostHolder.setUser(user);
            }
            // 如果凭证过期了，但状态仍为0（有效），则修改状态为1（无效）
            if (loginTicket != null && (loginTicket.getStatus() == 0 && loginTicket.getExpired().before(new Date()))) {
                loginTicketMapper.updateStatus(ticket, 1);
            }
        }
        return true;
    }

    // 这里只有postHandle()方法中有ModelAndView对象，而Thymeleaf渲染需要在请求域中存放user，因此只能在这个拦截器中将线程私有的User对象放到请求域中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    // 在渲染完毕后，将线程私有的User对象删除
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
