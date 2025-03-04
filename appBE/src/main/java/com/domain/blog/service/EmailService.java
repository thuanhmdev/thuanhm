package com.domain.blog.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    void sendEmailFromTemplateSync(String to, String subject, String templateName, Object contextObject);
}