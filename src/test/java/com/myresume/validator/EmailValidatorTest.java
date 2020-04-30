package com.myresume.validator;


import com.myresume.annotation.constraints.Email;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmailValidatorTest {

    private ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
    private Email email = mock(Email.class);
    private EmailValidator emailValidator;

    private static final int MAX_EMAIL_LENGTH = 15;
    private static final int MIN_EMAIL_LENGTH = 3;

    @Before
    public void init(){
        emailValidator = new EmailValidator();
        when(email.maxSize()).thenReturn(MAX_EMAIL_LENGTH);
        when(email.minSize()).thenReturn(MIN_EMAIL_LENGTH);
        emailValidator.initialize(email);
    }

    @Test
    public void isValid_returnFalse_whenLengthSmaller() {
        String email_small_length = "a@";
        assertFalse(emailValidator.isValid(email_small_length, context));
    }

    @Test
    public void isValid_returnFalse_whenLengthBigger() {
        String email_big_length = "a123456789@123456789";
        assertFalse(emailValidator.isValid(email_big_length, context));
    }

    @Test
    public void isValid_returnFalse_whenDontHaveAtChar() {
        String email_without_at = "testemail.com";
        assertFalse(emailValidator.isValid(email_without_at, context));
    }

    @Test
    public void isValid_returnTrue_whenCorrectEmail() {
        String validMail = "walid@mail.com";
        assertTrue(emailValidator.isValid(validMail, context));
    }
}