package com.myresume.validator;

import com.myresume.annotation.constraints.MinUpperCharCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinUpperCharCountConstraintValidator implements ConstraintValidator<MinUpperCharCount, CharSequence> {

    private int minUpperCharCount;

    @Override
    public void initialize(MinUpperCharCount constraintAnnotation) {
        this.minUpperCharCount = constraintAnnotation.minUpperCharCount();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;

        for (int i = 0; i < charSequence.length(); i++) {
            if (Character.isUpperCase(charSequence.charAt(i))) {
                count++;
            }
        }

        return minUpperCharCount >= count;
    }
}
