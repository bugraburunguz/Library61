package com.library.validation.impl;

import org.passay.*;

public class PasswordConstraintValidator {

    public static boolean isValid(final String password) {

        PasswordValidator passwordValidator = getPasswordValidator();
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        return result.isValid();
    }

    private static PasswordValidator getPasswordValidator() {
        return new PasswordValidator(
                new LengthRule(8, 20),
                new WhitespaceRule(),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, false)
        );
    }
}
