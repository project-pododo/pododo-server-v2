package com.pododoserver.account.dto;

import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Getter
public class SignUpRequest {

    private String id;
    private String password;

    public SignUpRequest(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String validate() {
        if(id == null || id.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) return ErrorMessage.REQUIRED_EMPTY_PARAM.getMessage();

        if(!idValidate(id)) return ErrorMessage.INVALID_ID_FORMAT.getMessage();

        int count = getPasswordValidateCounts(password);
        if(count == 4) return BaseMessage.SUCCESS_REGISTER.getMessage();

        return ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage();
    }

    private static boolean idValidate(String id) {
        final String EMAIL_REGEX =  "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        final Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(id).matches();
    }

    private static int getPasswordValidateCounts(String s) {
        int metCount = 0;
        if(s.length() >= 8) metCount++;
        if(containsUpperCaseCriteria(s)) metCount++;
        if(containingNumberCriteria(s)) metCount++;
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
