package com.myresume.validator;

import com.myresume.annotation.constraints.Adulthood;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

        Date date = new GregorianCalendar(2000, Calendar.FEBRUARY, 1).getTime();
        assertTrue(adulthoodConstraintValidator.isValid(date, context));
    }

    @Test
    public void isValid_returnFalse_whenUserDontGetAge() {

        adulthoodConstraintValidator.initialize(adulthood);

        Date date = new Date(System.currentTimeMillis());
        assertFalse(adulthoodConstraintValidator.isValid(date, context));
    }
}
