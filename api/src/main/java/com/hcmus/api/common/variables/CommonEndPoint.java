package com.hcmus.api.common.variables;

import org.springframework.http.HttpMethod;

public enum CommonEndPoint {
    LOGIN_END_POINT("/accounts/authentication", HttpMethod.POST),
    FORGOT_PASSWORD_MAIL_STEP("/accounts/password/otp", HttpMethod.POST),
    FORGOT_PASSWORD_OTP_STEP("/accounts/password/validation/otp", HttpMethod.POST),
    FORGOT_PASSWORD_MAIN_STEP("/accounts/password", HttpMethod.POST);

    private final String endPoint;
    private final HttpMethod httpMethod;

    CommonEndPoint(String endPoint, HttpMethod httpMethod) {
        this.endPoint = endPoint;
        this.httpMethod = httpMethod;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
}
