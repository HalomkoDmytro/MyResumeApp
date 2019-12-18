package com.myresume.service;

public interface SendEmailService {

    void sendHello();

    void prepareAndSend(String recipient, String message);
}
