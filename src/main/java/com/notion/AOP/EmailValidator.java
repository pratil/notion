package com.notion.AOP;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.notion.constants.Constants.Regex.EMAIL_PATTERN;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

}
