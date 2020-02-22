import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.blog.entity.Blog;
import ssm.blog.entity.PageBean;
import ssm.blog.entity.User;
import ssm.blog.service.BlogService;
import ssm.blog.service.UserService;
import ssm.blog.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/2 16:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告知junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class FindByEmailTest {

    @Autowired
    UserService userService;

    @Test
    public void Test(){

        User user = userService.FindByEmail("123123");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
    }

}
