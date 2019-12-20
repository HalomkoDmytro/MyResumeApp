package com.myresume.validator;

import com.myresume.annotation.constraints.Adulthood;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class AdulthoodConstraintValidatorTest {

    private ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
    private Adulthood adulthood = mock(Adulthood.class);
    private AdulthoodConstraintValidator adulthoodConstraintValidator;

    private static final int MIN_AGE = 18;

    @Before
    public void init() {
        Mockito.when(adulthood.adulthoodAge()).thenReturn(MIN_AGE);
        adulthoodConstraintValidator = new AdulthoodConstraintValidator();
    }

    @Test
    public void isValid_returnTrue_whenUserOlderThenAge() {
        adulthoodConstraintValidator.initialize(adulthood);

        Date date = new Date();
        adulthoodConstraintValidator.isValid(date, context);
        //todo
    }

    @Test
    public void isValid_returnFalse_whenUserDontGetAge() {

        adulthoodConstraintValidator.initialize(adulthood);

        Date date = new Date(System.currentTimeMillis());
        assertFalse(adulthoodConstraintValidator.isValid(date, context));
    }
}
