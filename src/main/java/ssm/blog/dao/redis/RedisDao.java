package ssm.blog.dao.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import ssm.blog.entity.Blog;
import ssm.blog.entity.Comment;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/9/30 22:17
 */
@Repository
public class RedisDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisPool jedisPool;

    private RuntimeSchema<Comment> schema = RuntimeSchema.createFrom(Comment.class);

    public String getCode(String email){
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
//                String key = "comment:"+commentContent;
//                String key = "comment";
                //并没有实现内部序列化操作
                //get->byte[]->反序列化->Object(Blog)
                //采用自定义的序列化方式
                //protostuff:pojo
//                byte[] bytes = jedis.get(key.getBytes());
//                if(bytes!=null){
//                    //空对象
//                    Comment comment = schema.newMessage();
//                    ProtostuffIOUtil.mergeFrom(bytes,comment,schema);
//                    return comment;//seckill被反序列
//                }
                String code = jedis.get(email);
                return code;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    public String putCode(String email, String code){
        //set Object(Blog)->序列化->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
//                String key = "comment:"+comment.getContent();
//                byte[] bytes = ProtostuffIOUtil.toByteArray(comment,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

//                String result = jedis.set(key.getBytes(),bytes);
                int time = 60*10;
                String result = jedis.setex(email,time,code);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    public void loseCode(String email){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.del(email);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void clickHit(String clickHit){
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                jedis.incr(clickHit);
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

}
