package com.myresume.validator;

import com.myresume.annotation.constraints.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private int maxSize;

    @Override
    public void initialize(Email constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.length() >= maxSize) {
            return false;
        }

        return s.contains("@");
    }
}
