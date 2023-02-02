package com.hcmus.api.common.variables;

public enum Role {
    ROLE_CUSTOMER("Customer"),
    ROLE_EMPLOYEE("Employee"),
    ROLE_ADMIN("Admin"),
    NO_ROLE("");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
