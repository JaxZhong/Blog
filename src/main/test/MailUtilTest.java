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
//��֪junit spring�����ļ�
@ContextConfiguration({"classpath:applicationContext.xml"})
public class MailUtilTest {
//    @Autowired
//    private JavaMailSender javaMailSender;//��spring�����õ��ʼ����͵�bean
//
//    @Test
//    public void sendMail03(){
//        MimeMessage mMessage=javaMailSender.createMimeMessage();//�����ʼ�����
//        MimeMessageHelper mMessageHelper;
//        try {
//            mMessageHelper=new MimeMessageHelper(mMessage,true);
//            mMessageHelper.setFrom("1076481671@qq.com");
//            mMessageHelper.setTo("zzq772868@126.com");//�ռ�������
//            mMessageHelper.setSubject("Spring���ʼ�����");//�ʼ�������
//            mMessageHelper.setText("<p>����ʹ��spring���ʼ����ܷ��͵�һ���ʼ�</p><br/>" +
//                    "<a href='https://blog.csdn.net/Mr__Viking'>���ҵĲ�����ҳ</a><br/>" +
//                    "<img src='cid:fengye'>",true);//�ʼ����ı����ݣ�true��ʾ�ı���html��ʽ��
////            File file=new File("F:/img/��Ҷ.png");//���ʼ������һ��ͼƬ
////            FileSystemResource resource=new FileSystemResource(file);
////            mMessageHelper.addInline("fengye", resource);//����ָ��һ��id,����������
////            mMessageHelper.addAttachment("��Ҷ.png", resource);//���ʼ������һ������
//            javaMailSender.send(mMessage);//�����ʼ�
//            System.out.println("���ͳɹ�");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("���ͳɹ�");
//    }



//    @Test
//    public void test() {
//        try {
//            MailUtil.sendEmail("zzq772868@126.com","123543");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("--------------------------���ͳɹ�");
//    }
}
