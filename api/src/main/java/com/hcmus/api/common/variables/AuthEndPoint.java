package com.hcmus.api.common.variables;

import org.springframework.http.HttpMethod;

public enum AuthEndPoint {
    CHANGE_PASSWORD_END_POINT("/accounts/**/password", Role.NO_ROLE, HttpMethod.PUT),
    GET_BANKING_ACCOUNTS_BY_USER_ID("/users/**/bankingAccounts", Role.ROLE_CUSTOMER, HttpMethod.GET),
    GET_CONTACTS_BY_USER_ID("/users/**/contacts", Role.ROLE_CUSTOMER, HttpMethod.GET),
    UPDATE_CONTACT_BY_CONTACT_ID("/users/**/contacts/**", Role.ROLE_CUSTOMER, HttpMethod.PUT),
    DELETE_CONTACT_BY_CONTACT_ID("/users/**/contacts/**", Role.ROLE_CUSTOMER, HttpMethod.DELETE),
    ADD_NEW_CONTACT("/users/**/contacts", Role.ROLE_CUSTOMER, HttpMethod.POST);

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
