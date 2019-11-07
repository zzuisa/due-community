package cn.zzuisa.community.dao;

import cn.zzuisa.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ao
 * @date 2019-11-07 14:26
 */
@Mapper
@Repository
public interface CommentMapper {
    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);



}
