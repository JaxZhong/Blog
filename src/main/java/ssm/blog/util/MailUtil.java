package ssm.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/3 11:35
 */
public class MailUtil {

    @Autowired
    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean

    @Value("${mail.smtp.username}")
    private static String emailFrom;

    public  void sendEmail(String email, String code) throws MessagingException {
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;

            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(emailFrom);
            mMessageHelper.setTo(email);//收件人邮箱
            mMessageHelper.setSubject("钟智强的博客网站注册");//邮件的主题
            mMessageHelper.setText("<p>您的注册验证为" + code + "</p><br/>" +
                    "<a href='https://www.xzhi.club/Blog'>打开钟智强博客主页</a><br/>",true);//邮件的文本内容，true表示文本以html格式打开
//            File file=new File("F:/img/枫叶.png");//在邮件中添加一张图片
//            FileSystemResource resource=new FileSystemResource(file);
//            mMessageHelper.addInline("fengye", resource);//这里指定一个id,在上面引用
//            mMessageHelper.addAttachment("枫叶.png", resource);//在邮件中添加一个附件
            javaMailSender.send(mMessage);//发送邮件
            System.out.println("发送成功");

    }
}
