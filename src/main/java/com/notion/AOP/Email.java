package com.notion.AOP;

import org.springframework.core.annotation.AliasFor;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.notion.constants.Constants.UserException.INVALID_EMAIL_EXCEPTION;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {

    @AliasFor("message")
    String value() default INVALID_EMAIL_EXCEPTION;

    @AliasFor("value")
    String message() default INVALID_EMAIL_EXCEPTION;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
