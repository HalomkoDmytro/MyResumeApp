package com.myresume.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SendEmailServiceImplTest {

    @InjectMocks
    private SendEmailServiceImpl sendEmailService;

    @Mock
    private ExecutorService executorService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private MailContentBuilder mailContentBuilder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void prepareAndSend_callMethods() {
        sendEmailService.prepareAndSend("test", "test");
        verify(mailContentBuilder, times(1)).buildSimpleMessageContent(anyString(), anyString());
        verify(executorService, times(1)).submit((Runnable) any());
    }

    @Test
    public void prepareAndSend_activationEmail() {
        sendEmailService.activationEmail("test", "test");
        verify(mailContentBuilder, times(1)).buildSimpleMessageContent(anyString(), anyString());
        verify(executorService, times(1)).submit((Runnable) any());
    }

}