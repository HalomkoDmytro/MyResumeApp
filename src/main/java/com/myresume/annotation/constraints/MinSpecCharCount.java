package com.myresume.annotation.constraints;


import com.myresume.validator.MinSpecCharCountConstraintValidator;

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
@Constraint(validatedBy={MinSpecCharCountConstraintValidator.class})
public @interface MinSpecCharCount {

    String message() default "MinSpecCharCount";

    String specSymbols() default "!@~`#$%^&*()_-+=|\\/{}[].,;:/?";

    int minSpecCharCount() default 1;

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
