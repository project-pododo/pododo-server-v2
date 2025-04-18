package com.pododoserver.account;

import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.constant.ErrorMessage;

public class PasswordValidator {

    public String validate(String s) {
        if(s == null || s.trim().isEmpty()) return ErrorMessage.REQUIRED_EMPTY_PARAM.getMessage();

        int count = getPasswordValidateCounts(s);
        if(count == 4) return BaseMessage.SUCCESS_REGISTER.getMessage();

        return ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage();
    }

    private static int getPasswordValidateCounts(String s) {
        int metCount = 0;
        if(s.length() >= 8) metCount++;
        if(containingNumberCriteria(s)) metCount++;
        if(containsUpperCaseCriteria(s)) metCount++;
        if(containIngSymbolCriteria(s)) metCount++;
        return metCount;
    }

    private static boolean containsUpperCaseCriteria(String s) {
        for (char a : s.toCharArray()) {
            if (Character.isUpperCase(a)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containingNumberCriteria(String s) {
        for (char a : s.toCharArray()) {
            if (a >= '0' && a <= '9') {
                return true;
            }
        }
        return false;
    }

    private static boolean containIngSymbolCriteria(String s) {
        String includeSpecialLetterRegex = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$";
        if(s.matches(includeSpecialLetterRegex)) return true;
        return false;
    }
}
