package com.myresume.validator;


import com.myresume.annotation.constraints.EnglishLanguage;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnglishLanguageConstrainValidatorTest {

    private ConstraintValidatorContext context = mock(ConstraintValidatorContext.class);
    private EnglishLanguageConstrainValidator languageValidator;
    private EnglishLanguage englishLanguage = mock(EnglishLanguage.class);

    @Before
    public void init() {
        languageValidator = new EnglishLanguageConstrainValidator();
    }

    @Test
    public void isValid_returnFalse_whenStringContainsNotEngChar() {
        String text_not_english = "выфва";
        assertFalse(languageValidator.isValid(text_not_english, context));
    }

    @Test
    public void isValid_returnTrue_whenStringContainsValidEngText() {
        String valid_eng_text = "abs";
        assertTrue(languageValidator.isValid(valid_eng_text, context));
    }

    @Test
    public void isValid_returnTrue_whenStringContainsValidEngTextWithNumbers() {
        String valid_eng_text = "abs123";
        when(englishLanguage.withNumber()).thenReturn(true);
        languageValidator.initialize(englishLanguage);
        assertTrue(languageValidator.isValid(valid_eng_text, context));
    }

    @Test
    public void isValid_returnFalse_whenStringContainsEngTextWithNumbers() {
        String not_valid_eng_text = "abs123";
        when(englishLanguage.withNumber()).thenReturn(false);
        languageValidator.initialize(englishLanguage);
        assertFalse(languageValidator.isValid(not_valid_eng_text, context));
    }

    @Test
    public void isValid_returnTrue_whenStringContainsValidEngTextWithSpecSymbol() {
        String valid_eng_text = "asd~#$%^&*-+=_dsjfk";
        when(englishLanguage.withSpechSymbols()).thenReturn(true);
        languageValidator.initialize(englishLanguage);
        assertTrue(languageValidator.isValid(valid_eng_text, context));
    }

    @Test
    public void isValid_returnFalse_whenStringContainsEngTextWithSpecSymbol() {
        String not_valid_eng_text = "asdb~#$%^&*-+=_jdkfl";
        when(englishLanguage.withSpechSymbols()).thenReturn(false);
        languageValidator.initialize(englishLanguage);
        assertFalse(languageValidator.isValid(not_valid_eng_text, context));
    }

    @Test
    public void isValid_returnTrue_whenStringContainsValidEngTextWithPunctuation() {
        String valid_eng_text = "asd.,?!-:()asdf";
        when(englishLanguage.withPunctuations()).thenReturn(true);
        languageValidator.initialize(englishLanguage);
        assertTrue(languageValidator.isValid(valid_eng_text, context));
    }

    @Test
    public void isValid_returnFalse_whenStringContainsEngTextWithPunctuation() {
        String not_valid_eng_text = "asdf.,?!-:()asdfaf";
        when(englishLanguage.withPunctuations()).thenReturn(false);
        languageValidator.initialize(englishLanguage);
        assertFalse(languageValidator.isValid(not_valid_eng_text, context));
    }

}