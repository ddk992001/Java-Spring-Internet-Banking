package com.hcmus.api.common;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtils {
    private static final int SALT_ROUNDS = 10;

    public static boolean isSamePassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    public static String hashPassword(String password) {
        return "";
    }
}
