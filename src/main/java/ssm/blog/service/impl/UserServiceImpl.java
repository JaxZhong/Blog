package ssm.blog.service.impl;

import org.springframework.stereotype.Service;
import ssm.blog.dao.UserDao;
import ssm.blog.entity.Blog;
import ssm.blog.entity.User;
import ssm.blog.service.UserService;
import ssm.blog.util.CryptographyUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
        String password = user.getPassword();
        String newPassword = CryptographyUtil.md5(password, "javacoder");
        user.setPassword(newPassword);
        return userDao.addUser(user);
    }

    @Override
    public List<Blog> listUser(Map<String, Object> map) {
        return userDao.listUser(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return userDao.getTotal(map);
    }

    @Override
    public Integer deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    @Override
    public Integer updateUser(User user) {
        String password = user.getPassword();
        String newPassword = CryptographyUtil.md5(password, "javacoder");
        user.setPassword(newPassword);
        return userDao.updateUser(user);
    }
}
