package com.pododoserver.common.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

public class JaysptUtilTest {

    @Test
    void jasypt() {
        String url = "Host";
        String username = "User";
        String password = "Password";
        String encryptedUrl = jasyptEnc(url);
        String encryptedUsername = jasyptEnc(username);
        String encryptedPassword = jasyptEnc(password);

        System.out.println("Encrypted URL: " + encryptedUrl);
        System.out.println("Encrypted Username: " + encryptedUsername);
        System.out.println("Encrypted Password: " + encryptedPassword);

        System.out.println("Decrypted URL: " + jasyptDec(encryptedUrl));
        System.out.println("Decrypted Username: " + jasyptDec(encryptedUsername));
        System.out.println("Decrypted Password: " + jasyptDec(encryptedPassword));

        String ymlEncryptedPassword = "ENC..";
        System.out.println("Decrypted yml password: " + jasyptDec(ymlEncryptedPassword));
    }

    public String jasyptEnc(String VALUE) {
        String key = "privateKey";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(key);
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        return encryptor.encrypt(VALUE);
    }

    public String jasyptDec(String encryptedValue) {
        String key = "privateKey";
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(key);
        config.setKeyObtentionIterations(1000);
        config.setPoolSize(1);
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");

        encryptor.setConfig(config);
        try {
            return encryptor.decrypt(encryptedValue);
        } catch (Exception e) {
            System.err.println("복호화 실패: " + e.getMessage());
            return "복호화 실패: " + encryptedValue;
        }
    }
}
