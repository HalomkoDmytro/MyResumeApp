package com.myresume.annotation.constraints;

import com.myresume.validator.FieldMatchConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,  ANNOTATION_TYPE,})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FieldMatchConstraintValidator.class})
public @interface FieldMatch {

    String message() default "FieldMatch";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();

    String second();

    /**
     * Defines several <code>@FieldMatch</code> annotations on the same element
     *
     * @see FieldMatch
     */
    @Target({ TYPE, ANNOTATION_TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
