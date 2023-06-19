package com.library.validation.impl;

import java.util.regex.Pattern;

public class MobileValidator {

    private static final String MOBILE_PATTERN = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

    public static boolean validateMobile(String mobileNumber) {
        return Pattern.compile(MOBILE_PATTERN)
                .matcher(mobileNumber)
                .matches();
    }

}
