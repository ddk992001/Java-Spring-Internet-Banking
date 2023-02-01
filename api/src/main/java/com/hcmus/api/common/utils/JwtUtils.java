package com.hcmus.api.common.utils;

import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.common.variables.Time;
import com.hcmus.api.exception.GenericException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    private static final String SECRET_KEY = "DangDuyKhang09092001";

    public static String createAccessToken(String payload) {
        Date current = new Date();
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(current)
                .setExpiration(new Date(current.getTime() + Time.ACCESS_TOKEN_EXPIRE_AFTER_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean isValidToken(String accessToken) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsernameFromToken(String accessToken) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }
}
