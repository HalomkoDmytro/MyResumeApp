package com.myresume.annotation.constraints;

import com.myresume.validator.EnglishLanguageConstrainValidator;

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
@Constraint(validatedBy = {EnglishLanguageConstrainValidator.class})
public @interface EnglishLanguage {

    String message() default "EnglishLanguage";

    boolean withNumber() default true;

    boolean withPunctuations() default true;

    boolean withSpechSymbols() default true;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
