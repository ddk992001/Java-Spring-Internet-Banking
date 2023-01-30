package com.hcmus.api.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "DangDuyKhang09092001";
    private static final long EXPIRE_TIME = 3600000L; // 1 hour

    public static String createAccessToken(String payload) {
        Date current = new Date();
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(current)
                .setExpiration(new Date(current.getTime() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
