package com.myresume.validator;

import com.myresume.annotation.constraints.FirstFieldLessThanSecond;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class FirstFieldLessThanSecondConstraintValidator implements ConstraintValidator<FirstFieldLessThanSecond, Object> {
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FirstFieldLessThanSecond constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstValue = BeanUtils.getPropertyDescriptor(o.getClass(), firstFieldName).getReadMethod().invoke(o);
            Object secondValue = BeanUtils.getPropertyDescriptor(o.getClass(), secondFieldName).getReadMethod().invoke(o);

            if (firstValue == null || secondValue == null) {
                return false;
            } else if (firstValue instanceof Comparable<?> && secondValue instanceof Comparable<?>) {
                return ((Comparable<Object>) firstValue).compareTo(secondValue) <= 0;
            } else {
                throw new IllegalArgumentException("First second fields are not comparable!");
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            return false;
        }
    }
}
