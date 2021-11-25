package com.carrefour.renfortapp.controlleur;

import com.carrefour.renfortapp.models.Mail;
import com.carrefour.renfortapp.utils.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/users/emails")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public String sendMail(){
        System.out.println("Spring Mail - Sending Simple Email with JavaMailSender Example");
        Mail mail = new Mail();
        mail.setFrom("essidnissaf@gmail.com");
        mail.setTo("hamzaouirourou2@gmail.com");
        mail.setSubject("Sending Simple Email with JavaMailSender Example");
        mail.setContent("This tutorial demonstrates how to send a simple email using Spring Framework.");
        emailService.sendSimpleMessage(mail);
        return "ok";
    }

}
