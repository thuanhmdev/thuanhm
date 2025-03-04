package com.domain.blog.service.Impl;

import com.domain.blog.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;


@Service
@RequiredArgsConstructor
@Slf4j(topic="EMAIL-SERVICE")
@Tag(name="EMAIL-SERVICE")
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml)   {
        MimeMessage mineMessage = javaMailSender.createMimeMessage();
           try {
               MimeMessageHelper helper = new MimeMessageHelper(mineMessage, isMultipart, StandardCharsets.UTF_8.name());
               helper.setTo(to);
               helper.setTo(to);
               helper.setSubject(subject);
               helper.setText(content, isHtml);

               javaMailSender.send(mineMessage);
           } catch (MailException | MessagingException e) {
               System.out.println("ERROR SEND EMAIL: " + e);
           }
    }
    @Async
    public void sendEmailFromTemplateSync(String to, String subject, String templateName, Object contextObject)   {
        Context context = new Context();
        context.setVariable("context", contextObject);
        String content = springTemplateEngine.process(templateName, context);
        sendEmailSync(to, subject, content, false, true);
    }
}
