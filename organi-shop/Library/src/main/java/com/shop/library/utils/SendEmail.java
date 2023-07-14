package com.shop.library.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class SendEmail {
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String fromEmail, String subject, String body){

            SimpleMailMessage message = new SimpleMailMessage();
            message.setReplyTo(fromEmail);
            message.setTo("ngoc3398@gmail.com");
            message.setText("Email: " + fromEmail + '\n' + body);
            message.setSubject(subject);
        System.out.println(message);
        try{
            this.mailSender.send(message);

            System.out.println("sending mail successfully");
        } catch (MailException e){
            System.out.println(e.getMessage());
            throw new HttpClientErrorException(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
