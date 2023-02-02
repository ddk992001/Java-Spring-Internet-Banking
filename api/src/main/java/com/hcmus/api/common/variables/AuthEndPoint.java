package com.hcmus.api.common.variables;

import org.springframework.http.HttpMethod;

public enum AuthEndPoint {
    CHANGE_PASSWORD_END_POINT("/accounts/{username}/password", Role.NO_ROLE, HttpMethod.PUT);

    private final String endPoint;
    private final Role role;
    private final HttpMethod httpMethod;

    AuthEndPoint(String endPoint, Role role, HttpMethod httpMethod) {
        this.endPoint = endPoint;
        this.role = role;
        this.httpMethod = httpMethod;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public Role getRole() {
        return this.role;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }
}
