package cn.zzuisa.community.controller.interceptor;

import cn.zzuisa.community.entity.LoginTicket;
import cn.zzuisa.community.entity.User;
import cn.zzuisa.community.service.UserService;
import cn.zzuisa.community.util.CookieUtil;
import cn.zzuisa.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Ao
 * @date 2019-10-27 14:34
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {


    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get ticket from cookie
        String ticket = CookieUtil.getValue(request, "ticket");
        if (ticket != null) {
            // Query ticket
            LoginTicket loginTicket = userService.findLoginTicket(ticket);

            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                // Find user by ticket
                User user= userService.findUserById(loginTicket.getUserId());
                // Hold the user for this request
                hostHolder.setUser(user);

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
