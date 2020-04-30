package com.myresume.validator;

import com.myresume.annotation.constraints.MinSpecCharCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinSpecCharCountConstraintValidator implements ConstraintValidator<MinSpecCharCount, CharSequence> {

    private String specSymbols;
    private int minSpecCharCount;

    @Override
    public void initialize(MinSpecCharCount constraintAnnotation) {
        this.specSymbols = constraintAnnotation.specSymbols();
        this.minSpecCharCount = constraintAnnotation.minSpecCharCount();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;

        for (int i = 0; i < charSequence.length(); i++) {
            if (specSymbols.indexOf(charSequence.charAt(i)) != -1) {
                count++;
            }
        }

        return minSpecCharCount >= count;
    }
}
