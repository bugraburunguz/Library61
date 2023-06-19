package com.library.validation.impl;

public class GeneralValidator {

    public static boolean mobileValidator(String email) {
        return MobileValidator.validateMobile(email);
    }

    public static boolean emailValidator(String email) {
        return EmailValidator.isValid(email);
    }

    public static boolean passwordValidator(String email) {
        return PasswordConstraintValidator.isValid(email);
    }

}

