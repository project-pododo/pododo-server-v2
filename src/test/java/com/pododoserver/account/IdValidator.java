package com.pododoserver.account;

import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.constant.ErrorMessage;

import java.util.regex.Pattern;

public class IdValidator {
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static boolean validate(String email) {
        if (email == null || email.trim().isEmpty()) return false;
        return pattern.matcher(email).matches();
    }
}
