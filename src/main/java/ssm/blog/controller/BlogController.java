package ssm.blog.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ssm.blog.dao.redis.RedisDao;
import ssm.blog.entity.Blog;
import ssm.blog.entity.Comment;
import ssm.blog.lucene.BlogIndex;
import ssm.blog.service.BlogService;
import ssm.blog.service.CommentService;
import ssm.blog.util.PageUtil;
import ssm.blog.util.StringUtil;

/**
 * @Description 博客Controller层
 * @author Jax
 *
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	@Resource
	private CommentService commentService;
	@Resource
	private BlogIndex blogIndex;

	@Autowired
	private RedisDao redisDao;

	// 请求博客详细信息
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id") Integer id,
			HttpServletRequest request) {//此处request作为形参作用是获取容器位置，看104行request.getServletContext().getContextPath()，即jsp里的${pageContext.request.contextPath}作用

		ModelAndView modelAndView = new ModelAndView();

		Blog blog = blogService.findById(id);


		// 获取关键字
		String keyWords = blog.getKeyWord();
		if (StringUtil.isNotEmpty(keyWords)) {
			String[] strArray = keyWords.split(" ");
			List<String> keyWordsList = StringUtil.filterWhite(Arrays
					.asList(strArray));
			modelAndView.addObject("keyWords", keyWordsList);
		} else {
			modelAndView.addObject("keyWords", null);
		}

		modelAndView.addObject("blog", blog);
		blog.setClickHit(blog.getClickHit() + 1); // 将博客访问量加1
		blogService.update(blog); // 更新博客

		// 查询评论信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);
		List<Comment> commentList = commentService.getCommentData(map);

		modelAndView.addObject("commentList", commentList);
		modelAndView.addObject("commonPage", "foreground/blog/blogDetail.jsp");
		modelAndView.addObject("title", blog.getTitle() + " - Jax的博客");

		// 存入上一篇和下一篇的显示代码
		modelAndView.addObject("pageCode", PageUtil.getPrevAndNextPageCode(
				blogService.getPrevBlog(id), blogService.getNextBlog(id),
				request.getServletContext().getContextPath()));

		modelAndView.setViewName("mainTemp");

		return modelAndView;
	}

	// 根据关键字全局检索博客信息
	@RequestMapping("/search")
	public ModelAndView search(
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request) throws Exception {//此处request作为形参作用是获取容器位置，看104行request.getServletContext().getContextPath()，即jsp里的${pageContext.request.contextPath}作用

		int pageSize = 10;
		ModelAndView modelAndView = new ModelAndView();
		List<Blog> blogIndexList = blogIndex.searchBlog(q);
		if(page == null) { //page为空表示第一次搜索
			page = "1";
		}
		int fromIndex = (Integer.parseInt(page) - 1) * pageSize; // 开始索引
		int toIndex = blogIndexList.size() >= Integer.parseInt(page) * pageSize ? Integer
				.parseInt(page) * pageSize
				: blogIndexList.size();
		modelAndView.addObject("blogIndexList", blogIndexList.subList(fromIndex, toIndex));
		modelAndView.addObject("pageCode", PageUtil.getUpAndDownPageCode(
				Integer.parseInt(page), blogIndexList.size(), q, pageSize,
				request.getServletContext().getContextPath()));
		modelAndView.addObject("q", q); // 用于数据的回显
		modelAndView.addObject("resultTotal", blogIndexList.size()); // 查询到的总记录数
		modelAndView.addObject("commonPage", "foreground/blog/searchResult.jsp");
		modelAndView.addObject("title", "搜索'" + q + "'的结果 - Jax的博客");
		modelAndView.setViewName("mainTemp");
		return modelAndView;
	}

}
