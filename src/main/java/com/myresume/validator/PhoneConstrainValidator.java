package com.myresume.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.myresume.annotation.constraints.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneConstrainValidator implements ConstraintValidator<Phone, String> {

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return true;
        }
        try {
            final Phonenumber.PhoneNumber phoneNumber = PhoneNumberUtil.getInstance().parse(number, "");
            return PhoneNumberUtil.getInstance().isValidNumber(phoneNumber);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
