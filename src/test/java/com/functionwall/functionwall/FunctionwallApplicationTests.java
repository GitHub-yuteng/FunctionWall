package com.functionwall.functionwall;

import com.functionwall.dao.ComplaintWallMapper;
import com.functionwall.pojo.model.Topic;
import com.functionwall.service.TopicService;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@SpringBootTest
class FunctionwallApplicationTests {

    @Autowired
    public JavaMailSenderImpl mailSender;

    @Autowired
    TopicService topicService;

    @Test
    void mailSender() {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setSubject("Mail");
        mailMessage.setText("Test JavaMail");
        mailMessage.setTo("1095652840@qq.com");
        mailMessage.setFrom("995689575@qq.com");

//        mailSender.send(mailMessage);
        System.out.println("发送！");
    }

    @Test
    void complexMailSender() throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setSubject("Mail");
        helper.setText("Test MimeMessage");
        helper.addAttachment("1.jpeg", new File("src/main/resources/static/assets/images/blog-1.jpeg"));

        helper.setTo("995689575@qq.com");
        helper.setFrom("995689575@qq.com");

//        mailSender.send(mimeMessage);
        System.out.println("发送！");
    }


    @Test
    void testTopic() {
        for (int i = 0; i < 1; i++) {

        }
    }
}
