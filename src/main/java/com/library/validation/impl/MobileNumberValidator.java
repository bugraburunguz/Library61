package com.library.validation.impl;

import com.library.service.TranslateService;
import com.library.validation.ValidMobileNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, String> {

    private final Pattern mobileNumberPattern;
    private final TranslateService translateService;

    public MobileNumberValidator(Supplier<Pattern> mobileNumberPattern, TranslateService translateService) {
        this.mobileNumberPattern = mobileNumberPattern.get();
        this.translateService = translateService;
    }

    @Override
    public boolean isValid(final String mobileNumber, ConstraintValidatorContext context) {
        if (FALSE.equals(isValid(mobileNumber))) {
            validationErrorHandler(context);
            return false;
        }
        return true;
    }

    private Boolean isValid(final String mobileNumber) {
        return Optional.ofNullable(mobileNumber)
                .map(mobileNumberPattern::matcher)
                .map(Matcher::matches)
                .orElse(FALSE);
    }

    private void validationErrorHandler(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("INVALID_MOBILE_NUMBER")
                .addConstraintViolation();
    }
}
