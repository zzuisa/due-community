package cn.zzuisa.community.service;

import cn.zzuisa.community.dao.UserMapper;
import cn.zzuisa.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ao
 * @date 2019-10-24 00:30
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }
}
