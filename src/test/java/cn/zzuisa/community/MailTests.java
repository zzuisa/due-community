package cn.zzuisa.community;

import cn.zzuisa.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static cn.zzuisa.community.util.CommunityConstant.DEFAULT_EXPIRED_SECONDS;
import static cn.zzuisa.community.util.CommunityConstant.REMEMBER_EXPIRED_SECONDS;

/**
 * @author Ao
 * @date 2019-10-25 20:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    @Autowired
    private MailClient mailClient;
    @Test
    public void testTextMail(){
        mailClient.sendMail("572220216@qq.com","TEST","Welcome to DUE");
    }
    @Test
    public void t2() {
        System.out.println(new Date(System.currentTimeMillis()).toString());

        System.out.println(new Date(System.currentTimeMillis()+3600*24*24 *1000).toString());
    }
}
