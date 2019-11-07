package cn.zzuisa.community.util;

/**
 * @author Ao
 * @date 2019-10-25 22:28
 */
public interface CommunityConstant {
    int ACTIVATION_SUCCESS = 0;
    int ACTIVATION_REPEAT = 1;
    int ACTIVATION_FAILIUE = 2;

    // 默认状态的登录凭证的超时时间

    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    // 【记住我】状态下的登录凭证超时时间
    long REMEMBER_EXPIRED_SECONDS = 3600L * 24 * 30;

    // 实体类型： 帖子
    int ENTITY_TEXT_POST = 1;

    // 实体类型： 评论
    int ENTITY_TEXT_COMMENT = 2;
}
