package com.shop.customer.controller;

import com.shop.library.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class ContactController {
    @Autowired
    private SendEmail sendEmail;

    @GetMapping("/contact")
    public String getContact(){
        return "contact";
    }

    @PostMapping("/contact/send-mail")
    public String contactBySendMail(@RequestParam(name = "fromEmail")String fromEmail,
                                    @RequestParam(name = "subject") String subject,
                                    @RequestParam(name = "message") String message){
            sendEmail.sendEmail(fromEmail,subject, message);

        return "redirect:/contact";
    }
}
