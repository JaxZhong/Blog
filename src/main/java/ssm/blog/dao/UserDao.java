package ssm.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ssm.blog.entity.User;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 15:44
 */
public interface UserDao {

    public User FindByEmail(String email);

    public Integer addUser(User user);
}
