package com.pododoserver.account;


import com.pododoserver.account.dto.SignUpRequest;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.constant.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SignUpTest {

    IdValidator idValidator = new IdValidator();
    PasswordValidator passwordValidator = new PasswordValidator();

    @DisplayName("signUp 테스트")
    @Test
    void totalTest() {
        SignUpRequest userA = new SignUpRequest("eampcasnaverc.com", "a!b12zxcAB");
        String result = userA.validate();
        Assertions.assertEquals(result, ErrorMessage.INVALID_ID_FORMAT.getMessage());

        SignUpRequest userB = new SignUpRequest("eampcasna@verc.com", "a!b12zxcAB");
        String result2 = userB.validate();
        Assertions.assertEquals(result2, BaseMessage.SUCCESS_REGISTER.getMessage());
    }

    /**
     * id 규칙
     * email 형식
     */

    @DisplayName("이메일 형식 충족")
    @Test
    void idTest1() {
        boolean result1 = idValidator.validate("test@example.com");
        Assertions.assertEquals(result1, true);
        boolean result2 = idValidator.validate("user.name+tag@domain.co.uk");
        Assertions.assertEquals(result2, true);
        boolean result3 = idValidator.validate("abc_def@mail-server.io");
        Assertions.assertEquals(result3, true);
        boolean result4 = idValidator.validate("plainaddress.com");
        Assertions.assertEquals(result4, false);
        boolean result5 = idValidator.validate("no-at-symbol.com");
        Assertions.assertEquals(result5, false);
        boolean result6 = idValidator.validate("@no-user.com");
        Assertions.assertEquals(result6, false);
        boolean result7 = idValidator.validate("test@.com");
        Assertions.assertEquals(result7, false);
        boolean result8 = idValidator.validate("test@com");
        Assertions.assertEquals(result8, false);
        boolean result9 = idValidator.validate("test@domain.c");
        Assertions.assertEquals(result9, false);
        boolean result10 = idValidator.validate("kjwnaver.com");
        Assertions.assertEquals(result10, false);
    }

    /**
     * 비밀번호 규칙
     * 8자 이상
     * 특수문자
     * 대문자
     * 숫자
     *
     * 모두 부합해야 통과
     */

    @DisplayName("모두 부합하는 경우")
    @Test
    void test1() {
        String result = passwordValidator.validate("ab12!@AB");
        Assertions.assertEquals(result, BaseMessage.SUCCESS_REGISTER.getMessage());

        String result2 = passwordValidator.validate("1b12!@AB");
        Assertions.assertEquals(result2, BaseMessage.SUCCESS_REGISTER.getMessage());
    }

    @DisplayName("특수문자 미포함 / 나머지 충족")
    @Test
    void test2() {
        String result = passwordValidator.validate("1b12123AB");
        Assertions.assertEquals(result, ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage());
    }

    @DisplayName("숫자 미포함 / 나머지 충족")
    @Test
    void test3() {
        String result = passwordValidator.validate("Abasdwdwa!");
        Assertions.assertEquals(result, ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage());
    }

    @DisplayName("대문자 미포함 / 나머지 충족")
    @Test
    void test4() {
        String result = passwordValidator.validate("!1231231ad!");
        Assertions.assertEquals(result, ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage());
    }

    @DisplayName("빈값")
    @Test
    void test5() {
        String result = passwordValidator.validate("");
        Assertions.assertEquals(result, ErrorMessage.REQUIRED_EMPTY_PARAM.getMessage());
    }

    @DisplayName("8글자 미만")
    @Test
    void test6() {
        String result = passwordValidator.validate("!A123A");
        Assertions.assertEquals(result, ErrorMessage.INVALID_PASSWORD_FORMAT.getMessage());
    }
}
