package com.library.validation.impl;

import com.library.validation.ValidPassword;

import lombok.RequiredArgsConstructor;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(final String password, ConstraintValidatorContext context) {

        PasswordValidator passwordValidator = getPasswordValidator();
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        validationErrorHandler(context, result);
        return false;
    }

    private PasswordValidator getPasswordValidator() {
        return new PasswordValidator(
                new LengthRule(8, 20),
                new WhitespaceRule(),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false)
        );
    }

    private void validationErrorHandler(ConstraintValidatorContext context, RuleResult result) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessages(result.getDetails())).addConstraintViolation();
    }

    private String errorMessages(List<RuleResultDetail> details) {
        return details
                .stream()
                .map(this::resolve)
                .collect(Collectors.joining(","));
    }

    private String resolve(RuleResultDetail detail) {
        return detail.getErrorCode() + Arrays.toString(detail.getValues());
    }
}
