

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import ssm.blog.dao.BlogDao;
import ssm.blog.dao.redis.RedisDao;
import ssm.blog.entity.Blog;

import ssm.blog.entity.Comment;
import ssm.blog.service.impl.BlogServiceImpl;

import java.util.Set;


/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/9/30 22:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告知junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class TestRedis {


    @Autowired
    BlogDao blogDao;

    @Autowired
    JedisPool jedisPool;

    @Test
    public void Test(){

//        Blog blog = redisDao.getBlog(36);
//        if (blog==null){
//            blog = blogDao.findById(36);
//            if (blog!=null){
//                String result = redisDao.putBlog(blog);
//                System.out.println(result);
//                blog = redisDao.getBlog(id);
//                System.out.println(blog);
//            }
//        }
        Comment comment = new Comment();
        comment.setContent("wdwjhdkqhwdkqh");
//        redisDao.putComment(comment);
//        redisDao.getComment();
        Jedis jedis = jedisPool.getResource();
        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");
        jedis.set("k4","v4");

        jedis.keys("*");

    }
}
