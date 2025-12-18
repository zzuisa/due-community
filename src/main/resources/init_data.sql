-- Seed data for local development/demo
-- (Translated to English)

-- Users
INSERT INTO `user` (`id`,`username`,`password`,`salt`,`email`,`type`,`status`,`activation_code`,`header_url`,`create_time`) VALUES
(1,'SYSTEM','SYSTEM','SYSTEM','system@example.com',0,1,NULL,'http://static.nowcoder.com/images/head/notify.png','2019-04-13 02:11:03'),
(101,'alice','25ac0a2e8bd0f28928de3c56149283d6','49f10','alice@example.com',0,1,NULL,'http://images.nowcoder.com/head/100t.png','2019-04-03 04:04:55'),
(102,'bob','25ac0a2e8bd0f28928de3c56149283d6','49f10','bob@example.com',0,1,NULL,'http://images.nowcoder.com/head/200t.png','2019-04-03 04:04:55');

-- Posts
INSERT INTO `discuss_post` (`id`,`user_id`,`title`,`content`,`type`,`status`,`create_time`,`comment_count`,`score`) VALUES
(1001,'101','Spring Hiring Plan','The job market can be tough. This is a demo post in English for development/testing.',0,0,'2019-04-04 03:53:36',1,0.0);

-- Comments
INSERT INTO `comment` (`id`,`user_id`,`entity_type`,`entity_id`,`target_id`,`content`,`status`,`create_time`) VALUES
(2001,102,1,1001,0,'Welcome! Glad to see you here.',0,'2019-04-04 11:13:36');

-- Private messages
INSERT INTO `message` (`id`,`from_id`,`to_id`,`conversation_id`,`content`,`status`,`create_time`) VALUES
(3001,101,102,'101_102','Hi Bob, are you there?',1,'2019-04-05 14:49:15'),
(3002,102,101,'101_102','Hi Alice! Yes, what\'s up?',1,'2019-04-05 14:50:10');
