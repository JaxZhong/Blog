package ssm.blog.service;

import org.springframework.stereotype.Service;
import ssm.blog.entity.Blogger;

/**
 * @Description ����Service�ӿ�
 * @author Jax
 *
 */
public interface BloggerService {

	public Blogger getByUsername(String username);

	public Blogger getBloggerData();

	// ���²���������Ϣ
	public Integer updateBlogger(Blogger blogger);
}
