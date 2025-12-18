package cn.zzuisa.community.util;

/**
 * @author Ao
 * @date 2019-10-25 22:28
 */
public interface CommunityConstant {
    /**
     * Activation succeeded.
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * Activation repeated (already activated).
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * Activation failed.
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * Login ticket expiration time for the default (non-remember-me) mode.
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * Login ticket expiration time for remember-me mode.
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    /**
     * Entity type: post.
     */
    int ENTITY_TYPE_POST = 1;

    /**
     * Entity type: comment.
     */
    int ENTITY_TYPE_COMMENT = 2;

    /**
     * Entity type: user.
     */
    int ENTITY_TYPE_USER = 3;

    /**
     * Topic: comment.
     */
    String TOPIC_COMMENT = "comment";

    /**
     * Topic: like.
     */
    String TOPIC_LIKE = "like";

    /**
     * Topic: follow.
     */
    String TOPIC_FOLLOW = "follow";

    /**
     * System user ID.
     */
    int SYSTEM_USER_ID = 1;
}
