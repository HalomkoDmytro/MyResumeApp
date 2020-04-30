package com.myresume.validator;

import com.myresume.annotation.constraints.Adulthood;
import org.joda.time.DateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class AdulthoodConstraintValidator implements ConstraintValidator<Adulthood, Date> {

    private int adulthoodAge;

    @Override
    public void initialize(Adulthood constraintAnnotation) {
        this.adulthoodAge = constraintAnnotation.adulthoodAge();
    }

    @Override
    public boolean isValid(Date birthDay, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDay == null) {
            return true;
        } else {
            DateTime criticalPath = DateTime.now().minusYears(adulthoodAge);
            return birthDay.before(criticalPath.toDate());
        }
    }
}
