package com.myresume.annotation.constraints;

import com.myresume.validator.MinDigitCountConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy={MinDigitCountConstraintValidator.class})
public @interface MinDigitCount {

    String message() default "Password not fit min length constraint";

    int minLength() default 6;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
