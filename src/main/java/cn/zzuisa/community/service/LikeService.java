package cn.zzuisa.community.service;

import cn.zzuisa.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Ao
 * @date 2019-12-07 04:24
 */
@Service
public class LikeService {
    @Autowired
    RedisTemplate redisTemplate;

    // 点赞
    public void like(int userId, int entityType,int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        Boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
        if (isMember) {
            redisTemplate.opsForSet().remove(entityLikeKey, userId);
        } else {
            redisTemplate.opsForSet().add(entityLikeKey, userId);
        }

        // 查询实体点赞的数量



    }

    public long findEntityCount(int entityType, int entityId) {
       return redisTemplate.opsForSet().size(RedisKeyUtil.getEntityLikeKey(entityType, entityId));
    }

    // 查询对某实体的点赞状态
    public int findEntityLikeStatus(int userId, int entityType,int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

}
