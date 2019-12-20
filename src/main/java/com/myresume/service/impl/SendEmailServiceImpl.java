package com.myresume.service.impl;

import com.myresume.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Override
    public void sendHello() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("hagogip526@themail3.net");
        msg.setSubject("Testing");
        msg.setText("text of body");

        mailSender.send(msg);
    }

    @Override
    public void prepareAndSend(String recipient, String message) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Sample mail subject");

            String content = mailContentBuilder.buildSimpleMessageContent(message, "confirmProfileEmail");
            messageHelper.setText(content, true);
        };

        sendEmail(recipient, messagePreparator);

    }

    private void sendEmail(String recipient, MimeMessagePreparator messagePreparator) {
        try {
            mailSender.send(messagePreparator);
            LOGGER.info("Send mail to {}", recipient);
        } catch (MailException e) {
            LOGGER.error("Exception during send email to {}", recipient, e);
        }
    }


    @Override
    public void activationEmail(String recipient, String confirmationToken) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Confirmation email");

            String content = mailContentBuilder.buildSimpleMessageContent("http://localhost:8082/confirm-account?token=" + confirmationToken, "confirmationTokenMail");
            messageHelper.setText(content, true);
        };

        sendEmail(recipient, messagePreparator);

    }
}
