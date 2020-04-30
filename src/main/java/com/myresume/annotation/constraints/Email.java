package com.myresume.annotation.constraints;

import com.myresume.validator.EnglishLanguageConstrainValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnglishLanguageConstrainValidator.class})//todo check validate by
public @interface Email {

    String message() default "Email should contain '@'";

    int maxSize() default 100;

    int minSize() default 3;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
