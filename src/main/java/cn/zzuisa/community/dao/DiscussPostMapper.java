package cn.zzuisa.community.dao;

import cn.zzuisa.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ao
 * @date 2019-10-24 00:17
 */
@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param is used to alias a function
    // if there is only one param, and it used in one SQL, then there must a @Param(xx)
    int selectDiscussPostRows(@Param("userId") int userId);



}
