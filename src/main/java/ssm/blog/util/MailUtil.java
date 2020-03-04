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
    private JavaMailSender javaMailSender;//��spring�����õ��ʼ����͵�bean

    @Value("${mail.smtp.username}")
    private static String emailFrom;

    public  void sendEmail(String email, String code) throws MessagingException {
        MimeMessage mMessage=javaMailSender.createMimeMessage();//�����ʼ�����
        MimeMessageHelper mMessageHelper;

            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(emailFrom);
            mMessageHelper.setTo(email);//�ռ�������
            mMessageHelper.setSubject("����ǿ�Ĳ�����վע��");//�ʼ�������
            mMessageHelper.setText("<p>����ע����֤Ϊ" + code + "</p><br/>" +
                    "<a href='https://www.xzhi.club/Blog'>������ǿ������ҳ</a><br/>",true);//�ʼ����ı����ݣ�true��ʾ�ı���html��ʽ��
//            File file=new File("F:/img/��Ҷ.png");//���ʼ������һ��ͼƬ
//            FileSystemResource resource=new FileSystemResource(file);
//            mMessageHelper.addInline("fengye", resource);//����ָ��һ��id,����������
//            mMessageHelper.addAttachment("��Ҷ.png", resource);//���ʼ������һ������
            javaMailSender.send(mMessage);//�����ʼ�
            System.out.println("���ͳɹ�");

    }
}
