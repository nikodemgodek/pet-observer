package com.example.pet_observer.service;

import com.example.pet_observer.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AIService aiService;

    public void sendEmail(Email email, String htmlContent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

        mimeMessageHelper.setFrom(senderEmail);
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        //mimeMessageHelper.setText(email.getText(), true);
        mimeMessageHelper.setText(htmlContent, true);

        javaMailSender.send(message);
    }

    /*public void sendEmail(String to, String subject, String prompt) throws MessagingException {

        aiService.getChatResponse(prompt)
                .subscribe(content -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(content);
                    javaMailSender.send(message);
                });*
    }*/




}