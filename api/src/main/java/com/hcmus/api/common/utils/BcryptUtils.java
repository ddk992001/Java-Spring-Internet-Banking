package com.hcmus.api.common.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BcryptUtils {
    private static final int SALT_ROUNDS = 10;

    public static boolean isSameText(String plainText, String hashedText) {
        return BCrypt.checkpw(plainText, hashedText);
    }

    public static String hashText(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt(SALT_ROUNDS));
    }
}
