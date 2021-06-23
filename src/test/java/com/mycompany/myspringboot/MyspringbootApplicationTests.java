package com.mycompany.myspringboot;

import com.mycompany.myspringboot.user.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class MyspringbootApplicationTests {
    @Autowired
    private Person person;

    @Autowired
    private DataSource dataSource;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    void getValue(){
        System.out.println(person);
    }

    @Test
    public void sendMaill(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("注意");
        mailMessage.setText("？？？？");
        mailMessage.setFrom("869819435@qq.com");
        mailMessage.setTo("personal_mail_yang@163.com");
        //发送邮箱
        mailSender.send(mailMessage);
    }

    @Test
    public void sendMaill2() throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setSubject("注意");
        helper.setText("<b style='color:red'>???</b>",true);
        helper.setFrom("869819435@qq.com");
        helper.setTo("personal_mail_yang@163.com");
        helper.addAttachment("",new File("E:\\poo\\personal files\\实习资料\\学习\\LearnImage\\1368768-20190613220434628-1803630402.png"));
        mailSender.send(message);
    }
}
