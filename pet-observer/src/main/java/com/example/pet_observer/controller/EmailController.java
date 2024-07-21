package com.example.pet_observer.controller;

import com.example.pet_observer.model.Email;
import com.example.pet_observer.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /*@PostMapping("/send")
    public String sendMail(@RequestBody Email email) throws MessagingException {
        emailService.sendEmail(email);
        return "Email sent successfully";
    }*/
}
