package com.aashishgiri5.ecommerce.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class MailSenderTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ContentBuilder contentBuilder;

    @Async
    public void sendMail(String subject, String body, String recepient)
    {
        MimeMessagePreparator mimeMessagePreparator=mimeMessage -> {
            MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("TechNova@gmail.com");
            mimeMessageHelper.setTo(recepient);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(contentBuilder.sendContent(body));


        };
        try{
            javaMailSender.send(mimeMessagePreparator);
            System.out.println("Mail sent Successfully");
        }catch (Exception e)
        {
            System.out.println("Exception during mail");
        }

    }

}
