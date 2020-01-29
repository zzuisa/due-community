package cn.zzuisa.community.controller;

import cn.zzuisa.community.entity.Event;
import cn.zzuisa.community.entity.User;
import cn.zzuisa.community.event.EventProducer;
import cn.zzuisa.community.service.LikeService;
import cn.zzuisa.community.util.CommunityConstant;
import cn.zzuisa.community.util.CommunityUtil;
import cn.zzuisa.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ao
 * @date 2019-12-07 04:36
 */
@Controller
public class LikeController implements CommunityConstant {
    @Autowired
    private LikeService likeService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private EventProducer eventProducer;


    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId, int entityUserId,int postId) {
        User user = hostHolder.getUser();

        // 点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);
        // 触发点赞
        if (likeStatus == 1) {
            Event event = new Event();
            event.setEntityUserId(entityUserId)
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setData("postId",postId);

            eventProducer.fireEvent(event);

        }

        return CommunityUtil.getJSONString(0, null, map);
    }


}
