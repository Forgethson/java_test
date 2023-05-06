package com.wjd.nowcoder.util;

import com.wjd.nowcoder.entity.User;
import org.springframework.stereotype.Component;

/**
 * 起到容器的作用，持有用户信息, 用于代替session对象.
 * 利用ThreadLocal对象，实现线程隔离
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }

}
