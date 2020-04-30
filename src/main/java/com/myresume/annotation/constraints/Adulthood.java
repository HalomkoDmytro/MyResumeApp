package com.myresume.annotation.constraints;

import com.myresume.validator.AdulthoodConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {AdulthoodConstraintValidator.class})
public @interface Adulthood {

    String message() default "Adulthood";

    int adulthoodAge() default 18;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
