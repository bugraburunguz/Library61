package com.library.validation.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Boolean.FALSE;

public class GeneralValidator {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final String MOBILE_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    public static boolean validateEmail(String emailAddress) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean validateMobile(String mobileNumber) {
        return Pattern.compile(MOBILE_PATTERN)
                .matcher(mobileNumber)
                .matches();
    }
}

