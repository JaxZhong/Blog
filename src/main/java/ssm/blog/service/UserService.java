package ssm.blog.service;

import org.springframework.stereotype.Service;
import ssm.blog.entity.User;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 15:48
 */
public interface UserService {

    public User FindByEmail(String email);

    public Integer addUser(User user);
}
