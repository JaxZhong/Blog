package ssm.blog.service.impl;

import org.springframework.stereotype.Service;
import ssm.blog.dao.UserDao;
import ssm.blog.entity.User;
import ssm.blog.service.UserService;

import javax.annotation.Resource;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 15:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Override
    public User FindByEmail(String email) {
        return userDao.FindByEmail(email);
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }
}
