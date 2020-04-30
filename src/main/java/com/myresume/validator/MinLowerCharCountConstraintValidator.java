package com.myresume.validator;

import com.myresume.annotation.constraints.MinLowerCharCount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MinLowerCharCountConstraintValidator implements ConstraintValidator<MinLowerCharCount, CharSequence> {

    private int minLowerCharCount;

    @Override
    public void initialize(MinLowerCharCount constraintAnnotation) {
        this.minLowerCharCount = constraintAnnotation.minLowerCharCount();
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;

        for (int i = 0; i < charSequence.length(); i++) {
            if (Character.isLowerCase(charSequence.charAt(i))) {
                count++;
            }
        }
        return minLowerCharCount >= count;
    }
}
