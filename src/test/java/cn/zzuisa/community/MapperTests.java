package cn.zzuisa.community;

import cn.zzuisa.community.dao.UserMapper;
import cn.zzuisa.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Ao
 * @date 2019-10-24 00:01
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = userMapper.selectById(101);
        System.out.println(user);

    }
}
