import org.junit.Test;
import ssm.blog.entity.Blog;
import ssm.blog.lucene.BlogIndex;

import java.util.List;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/9/29 21:41
 */
public class TestLucene {

    @Test
    public void Test(){
        BlogIndex blogIndex = new BlogIndex();
        try {
            List<Blog> blogs = blogIndex.searchBlog("fhf");
            for (Blog blog : blogs) {
                System.out.println(blog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
