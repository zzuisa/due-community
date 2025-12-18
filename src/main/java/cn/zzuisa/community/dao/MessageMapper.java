package cn.zzuisa.community.dao;

import cn.zzuisa.community.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ao
 * @date 2019-11-08 15:57
 */
@Mapper
@Repository
public interface MessageMapper {
    // Query the current user's conversation list; for each conversation only return the latest message.

    List<Message> selectConversations(int userId, int offset, int limit);

    // Query the number of conversations for the current user.

    int selectConversationsCount(int userId);

    // Query messages in a conversation.

    List<Message> selectLetters(String conversationId, int offset, int limit);

    // Query the total number of messages in a conversation.

    int selectLetterCount(String conversationId);

    // Query the number of unread private messages.

    int selectLetterUnreadCount(int userId,String conversationId);

    // Insert a new message.
    int insertMessage(Message message);

    // Update message status.
    int updateStatus(List<Integer> ids, int status);

    // Query the latest notice under a topic.
    Message selectLatestNotice(int userId, String topic);

    // Query the number of notices under a topic.
    int selectNoticeCount(int userId, String topic);

    // Query the number of unread notices under a topic.
    int selectNoticeUnreadCount(int userId, String topic);

    // Query the notice list under a topic.
    List<Message> selectNotices(int userId, String topic, int offset, int limit);
}
