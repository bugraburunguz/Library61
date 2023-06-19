package com.library.validation.impl;

import com.library.advice.exceptions.InvalidEmailAddressException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class EmailValidator {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isValid(final String email) {
        if (!StringUtils.hasLength(email)) {
            return false;
        }
        return validateEmail(email);
    }

    private static boolean validateEmail(final String email) {
        return pattern.matcher(email).matches();
    }

}
