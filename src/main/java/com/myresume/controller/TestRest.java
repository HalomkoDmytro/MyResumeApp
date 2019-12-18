package com.myresume.controller;

import com.myresume.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @Autowired
    private SendEmailService sendEmailService;

    @RequestMapping(value = "/sendemail")
    public String sendEmail() {
        sendEmailService.prepareAndSend("hagogip526@themail3.net","Privet!!!");
        return "Email sent successfully";
    }
}
