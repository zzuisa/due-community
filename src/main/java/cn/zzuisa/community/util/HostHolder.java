package cn.zzuisa.community.util;

import cn.zzuisa.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Ao
 * @date 2019-10-27 14:56
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
