package com.myresume.validator;

import com.myresume.annotation.constraints.MinDigitCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinDigitCountConstraintValidator implements ConstraintValidator<MinDigitCount, CharSequence> {

    private int minLength;

    @Override
    public void initialize(MinDigitCount constraintAnnotation) {
        this.minLength = constraintAnnotation.minLength();
    }

    @Override
    public boolean isValid(CharSequence val, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;
        for (int i = 0; i < val.length(); i++) {
            if (Character.isDigit(val.charAt(i))) {
                count++;
            }
        }

        return minLength <= count;
    }
}
