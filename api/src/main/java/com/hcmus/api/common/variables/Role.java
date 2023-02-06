package com.hcmus.api.common.variables;

public enum Role {
    NO_ROLE("", 0),
    ROLE_CUSTOMER("Customer", 1),
    ROLE_EMPLOYEE("Employee", 2),
    ROLE_ADMIN("Admin", 3);

    private final String name;
    private final int priority;

    Role(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }

    public static Role findRoleByName(String name) {
        for (Role role : Role.values()) {
            if (role.getName().equals(name))
                return role;
        }

        return null;
    }
}
