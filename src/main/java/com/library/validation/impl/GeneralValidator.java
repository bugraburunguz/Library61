package com.library.validation.impl;

import java.util.regex.Pattern;

public class GeneralValidator {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    public static boolean validateEmail(String emailAddress) {
        return Pattern.compile(EMAIL_PATTERN)
                .matcher(emailAddress)
                .matches();
    }


}

