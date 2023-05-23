package com.library.validation.impl;

import com.library.validation.ValidEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        if(!StringUtils.hasLength(email)) {
            validationErrorHandler(context, "EMAIL_IS_EMPTY");
            return false;
        }
        if (!validateEmail(email)){
            validationErrorHandler(context, "INVALID_EMAIL_ADDRESS");
            return false;
        }
        return true;
    }

    private boolean validateEmail(final String email) {
        return pattern.matcher(email).matches();
    }

    private void validationErrorHandler(ConstraintValidatorContext context, String code) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(code)
                .addConstraintViolation();
    }
}

