package ssm.blog.service;

import org.springframework.stereotype.Service;
import ssm.blog.entity.Blog;
import ssm.blog.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 15:48
 */
public interface UserService {

    public User FindByEmail(String email);

    public Integer addUser(User user);

    public List<Blog> listUser(Map<String, Object> map);

    public Long getTotal(Map<String, Object> map);

    public Integer deleteUser(Integer id);

    public Integer updateUser(User user);
}
