package cn.zzuisa.community.controller;

import cn.zzuisa.community.entity.Comment;
import cn.zzuisa.community.entity.DiscussPost;
import cn.zzuisa.community.entity.Page;
import cn.zzuisa.community.entity.User;
import cn.zzuisa.community.service.CommentService;
import cn.zzuisa.community.service.DiscussPostService;
import cn.zzuisa.community.service.LikeService;
import cn.zzuisa.community.service.UserService;
import cn.zzuisa.community.util.CommunityConstant;
import cn.zzuisa.community.util.CommunityUtil;
import cn.zzuisa.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author Ao
 * @date 2019-11-03 00:33
 */
@Controller
@RequestMapping("/discuss")
public class DIscussPostController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDisscussPost(String title, String content) {
        User user = hostHolder.getUser();
        if (user == null) {
            return CommunityUtil.getJSONString(403, "You are not logged in.");
        }
        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        System.out.printf("title:"+title);
        System.out.printf("content:"+content);
        discussPost.setTitle(title);
        discussPost.setContent(content);
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);
        return CommunityUtil.getJSONString(0, "Posted successfully!");
    }

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        // Post
        DiscussPost discussPost = discussPostService.findDiscussPost(discussPostId);
        model.addAttribute("post", discussPost);
        // Author
        User user = userService.findUserById(discussPost.getUserId());
        model.addAttribute("user", user);

        // Likes
        long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPostId);
        // Like status
        int likeStatus = hostHolder.getUser() == null ?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, discussPostId);


        model.addAttribute("likeCount", likeCount);
        model.addAttribute("likeStatus", likeStatus);
        // Comment pagination
        page.setLimit(5);
        page.setPath("/discuss/detail/"+discussPostId);
        page.setRows(discussPost.getCommentCount());
        List<Comment> commentsList= commentService.findCommentsByEntity(
                ENTITY_TYPE_POST, discussPost.getId(), page.getOffset(), page.getLimit());
        List<Map<String, Object>> commentVoList = new ArrayList<>();
        if (commentsList != null) {
            for (Comment comment : commentsList) {
                // Comment VO
                Map<String, Object> commentVo = new HashMap<>();
                // Comment
                commentVo.put("comment", comment);
                // Likes
                likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, comment.getId());
                // Like status
                likeStatus = hostHolder.getUser() == null ?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, comment.getId());
                System.err.println("likeCount: "+likeCount);
                commentVo.put("likeStatus", likeStatus);
                commentVo.put("likeCount", likeCount);
                // Author

                commentVo.put("user", userService.findUserById(comment.getUserId()));
                // Reply list
                List<Comment> replyList = commentService.findCommentsByEntity(
                        ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                // Reply VO list
                List<Map<String, Object>> replyVoList = new ArrayList<>();
                if (replyList != null) {
                    for (Comment reply : replyList) {
                        Map<String, Object> replyVo = new HashMap<>();
                        // Reply
                        replyVo.put("reply", reply);
                        // Author
                        // Likes
                        likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_COMMENT, reply.getId());
                        // Like status
                        likeStatus = hostHolder.getUser() == null ?0:likeService.findEntityLikeStatus(hostHolder.getUser().getId(), ENTITY_TYPE_POST, reply.getId());

                        replyVo.put("user", userService.findUserById(reply.getUserId()));
                        // Reply target
                        User target = reply.getTargetId() == 0 ? null : userService.findUserById(reply.getUserId());

                        replyVo.put("likeStatus", likeStatus);
                        replyVo.put("likeCount", likeCount);
                        replyVo.put("target", target);
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys", replyVoList);
                // Reply count
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVo.put("replyCount", replyCount);
                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comments", commentVoList);
        return "/site/discuss-detail";

    }

}
