package ssm.blog.controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.blog.dao.redis.RedisDao;
import ssm.blog.entity.User;
import ssm.blog.service.UserService;
import ssm.blog.util.ResponseUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/1 14:23
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean


    @RequestMapping("/login")
    public String userLogin (User user,
        HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        String email = user.getEmail();

        User user1 = userService.FindByEmail(email);

        if(null != user1 && user1.getPassword().equals(user.getPassword())){

            session.setAttribute("CurrentUser",user1);
            return "redirect:/a.jsp";
        } else {
            request.setAttribute("errorInfo","邮箱或密码错误");
            request.setAttribute("user",user);
            return "userLogin";
        }
    }

    @RequestMapping("/loginOut")
    public String loginOut ( HttpServletRequest request, HttpServletResponse response) throws Exception {


        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        session.invalidate();

        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;

    }

    @RequestMapping("/reg")
    public String reg (User user, @RequestParam("verCode") String verCode,HttpServletRequest request, HttpServletResponse response) throws Exception {

        JSONObject result = new JSONObject();

        String email = user.getEmail();
        int count = 0;
        if (verCode.equals(redisDao.getCode(email))){
            Integer integer = userService.addUser(user);
            redisDao.loseCode(email);
            if (integer != null){
                return "userLogin";
            }else {
                return "reg";
            }
        } else{//验证码错误次数判断
            ++count;
            if (count < 5) {
                result.put("errorInfo", "验证码错误");
                ResponseUtil.write(response, result);
                return null;
            }else {
                redisDao.loseCode(email);
                result.put("errorInfo", "验证码输入错误次数过多，请重新获取");
                ResponseUtil.write(response, result);
                return null;
            }
        }

    }

    @RequestMapping("/sendCode")
    public String sendCode(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int random6 = (int) ((Math.random() * 9 + 1) * 100000);
        String code = Integer.toString(random6);
        String email = user.getEmail();

        //发送邮件
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        String success = null;
        JSONObject result = new JSONObject();
        try {
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom("1076481671@qq.com");
            mMessageHelper.setTo(email);//收件人邮箱
            mMessageHelper.setSubject("钟智强的博客网站注册");//邮件的主题
            mMessageHelper.setText("<p>您的注册验证码为：" + code + "，有效时间为10分钟</p><br/>" +
                    "<a href='http://www.xzhi.club/Blog'>打开钟智强博客主页</a><br/>",true);//邮件的文本内容，true表示文本以html格式打开
            String OKString = redisDao.putCode(email, code);
            if ("OK".equals(OKString)) {
                javaMailSender.send(mMessage);//发送邮件
                //发送成功
                result.put("success", true);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
            result.put("success",false);
        }
        ResponseUtil.write(response, result);
        return null;
    }


    @RequestMapping("/goLogin")
    public String goLogin(){
        return "userLogin";
    }

    @RequestMapping("/goReg")
    public String goReg(){
        return "reg";
    }
}
