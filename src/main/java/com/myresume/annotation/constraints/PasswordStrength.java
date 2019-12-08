package com.myresume.annotation.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Size(min = 6, message = "Min length 6 character!")
@NotNull
@MinDigitCount
@MinUpperCharCount
@MinLowerCharCount
@MinSpecCharCount
public @interface PasswordStrength {

    String message() default "Password Strength";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
