package cn.zzuisa.community.controller;

import cn.zzuisa.community.entity.DiscussPost;
import cn.zzuisa.community.entity.Page;
import cn.zzuisa.community.entity.User;
import cn.zzuisa.community.service.DiscussPostService;
import cn.zzuisa.community.service.LikeService;
import cn.zzuisa.community.service.MessageService;
import cn.zzuisa.community.service.UserService;
import cn.zzuisa.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ao
 * @date 2019-10-24 00:34
 */
@Controller
public class HomeController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // before the function is called, SpringMVC is going to automatically initialize Model and Page, and autowires the Page into Model
        // so, we can directly access the data in Objective Page in thymeleaf
        page.setRows(discussPostService.findDiscussPostRows(0));
         page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list
            ) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount", likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        return "index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }
}
