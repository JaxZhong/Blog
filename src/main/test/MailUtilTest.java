import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ssm.blog.util.MailUtil;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: Jax_Zhong   email:zzq1076481671@gmail.com
 * @Date: 2019/10/3 11:35
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告知junit spring配置文件
@ContextConfiguration({"classpath:applicationContext.xml"})
public class MailUtilTest {
//    @Autowired
//    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean
//
//    @Test
//    public void sendMail03(){
//        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
//        MimeMessageHelper mMessageHelper;
//        try {
//            mMessageHelper=new MimeMessageHelper(mMessage,true);
//            mMessageHelper.setFrom("1076481671@qq.com");
//            mMessageHelper.setTo("zzq772868@126.com");//收件人邮箱
//            mMessageHelper.setSubject("Spring的邮件发送");//邮件的主题
//            mMessageHelper.setText("<p>这是使用spring的邮件功能发送的一封邮件</p><br/>" +
//                    "<a href='https://blog.csdn.net/Mr__Viking'>打开我的博客主页</a><br/>" +
//                    "<img src='cid:fengye'>",true);//邮件的文本内容，true表示文本以html格式打开
////            File file=new File("F:/img/枫叶.png");//在邮件中添加一张图片
////            FileSystemResource resource=new FileSystemResource(file);
////            mMessageHelper.addInline("fengye", resource);//这里指定一个id,在上面引用
////            mMessageHelper.addAttachment("枫叶.png", resource);//在邮件中添加一个附件
//            javaMailSender.send(mMessage);//发送邮件
//            System.out.println("发送成功");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("发送成功");
//    }



//    @Test
//    public void test() {
//        try {
//            MailUtil.sendEmail("zzq772868@126.com","123543");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("--------------------------发送成功");
//    }
}
