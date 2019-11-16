package com.myresume.validator;

import com.myresume.annotation.constraints.FieldMatch;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchConstraintValidator implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstValue = BeanUtils.getPropertyDescriptor(object.getClass(), firstFieldName).getReadMethod().invoke(object);
            Object secondValue = BeanUtils.getPropertyDescriptor(object.getClass(), secondFieldName).getReadMethod().invoke(object);
            return (firstValue == null && secondValue == null) || (firstValue != null && firstValue.equals(secondValue));
        } catch (Exception ignore) {
            return false;
        }
    }
}
