package ssm.blog.controller.admin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.blog.entity.Blog;
import ssm.blog.entity.BlogType;
import ssm.blog.entity.PageBean;
import ssm.blog.entity.User;
import ssm.blog.service.UserService;
import ssm.blog.util.DateJsonValueProcessor;
import ssm.blog.util.ResponseUtil;
import ssm.blog.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2020/3/6 01:26
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    //后台分页查询用户信息
    @RequestMapping("/listUser")
    public String listUser(
            @RequestParam(value="page", required=false)String page,
            @RequestParam(value="rows", required=false)String rows,
            User s_user,
            HttpServletResponse response) throws Exception {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", s_user.getEmail());
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Blog> listUser = userService.listUser(map);
        Long total = userService.getTotal(map);

        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(listUser, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        ResponseUtil.write(response, result);
        return null;
    }


    // 用户信息删除
    @RequestMapping("/delete")
    public String deleteBlog(
            @RequestParam(value="ids", required=false)String ids,
            HttpServletResponse response) throws Exception {

        String[] idsStr = ids.split(",");
        for(int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            userService.deleteUser(id);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }


    // 添加和更新用户
    @RequestMapping("/save")
    public String save(User user, HttpServletResponse response)
            throws Exception {

        int resultTotal = 0; // 接收返回结果记录数
        if (user.getId() == null) { // 说明是第一次插入
            resultTotal = userService.addUser(user);
        } else { // 有id表示修改
            resultTotal = userService.updateUser(user);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

}
