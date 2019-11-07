package cn.zzuisa.community;

import cn.zzuisa.community.dao.LoginTicketMapper;
import cn.zzuisa.community.entity.LoginTicket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Ao
 * @date 2019-10-26 21:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class LoginTicketTest {
    @Autowired
    LoginTicketMapper loginTicketMapper;
    @Test
    public void test1() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+1000*60*10));
        loginTicket.setUserId(101);
        loginTicket.setTicket("IJWRNFJ14235423TGEG513");
        loginTicketMapper.insertLoginTicket(loginTicket);
    }
}
