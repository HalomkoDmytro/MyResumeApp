package com.myresume.service.impl;

import com.myresume.entity.EmailModel;
import com.myresume.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailServiceImpl.class);

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Override
    public void prepareAndSend(String recipient, String message) {
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setTo(recipient);
//            messageHelper.setSubject("Sample mail subject");
//
//            String content = mailContentBuilder.buildSimpleMessageContent(message, "confirmProfileEmail");
//            messageHelper.setText(content, true);
//        };
//
//        sendEmail(recipient, messagePreparator);

        String content = mailContentBuilder.buildSimpleMessageContent(message, "confirmProfileEmail");
        final EmailModel emailModel = new EmailModel(recipient, "Sample mail subject", content);
        final EmailItem emailItem = new EmailItem(emailModel, 3);
        executorService.submit(emailItem);

    }
//
//    private void sendEmail(String recipient, MimeMessagePreparator messagePreparator) {
//        try {
//            mailSender.send(messagePreparator);
//            LOGGER.info("Send mail to {}", recipient);
//        } catch (MailException e) {
//            LOGGER.error("Exception during send email to {}", recipient, e);
//        }
//    }


    @Override
    public void activationEmail(String recipient, String confirmationToken) {
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setTo(recipient);
//            messageHelper.setSubject("Confirmation email");
//
//            messageHelper.setText(content, true);
//        };
        String content = mailContentBuilder.buildSimpleMessageContent(confirmationToken, "confirmationTokenMail");
        final EmailModel emailModel = new EmailModel(recipient, "onfirmation email", content);
        final EmailItem emailItem = new EmailItem(emailModel, 3);
        executorService.submit(emailItem);

//        sendEmail(recipient, messagePreparator);

    }

    private class EmailItem implements Runnable {

        private int tryCount;
        private EmailModel emailMessage;
        private MimeMessagePreparator messagePreparator;

        private EmailItem(EmailModel emailMessage, int tryCount) {
            this.emailMessage = emailMessage;
            this.tryCount = tryCount;
        }

        @Override
        public void run() {
            messagePreparator = mimeMessage -> {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setTo(emailMessage.getRecipient());
                helper.setSubject(emailMessage.getSubject());
                helper.setText(emailMessage.getContent(), true);
            };

            send();
        }

        private void send() {
            try {
                mailSender.send(messagePreparator);
                LOGGER.info("Send mail to {}", emailMessage.getRecipient());
            } catch (MailException ex) {
                tryCount--;
                if(tryCount > 0) {
                    send();
                }
                LOGGER.error("Exception during send email to {}. Email not send.", emailMessage.getRecipient(), ex);
            }
        }

    }
}
