package com.hcmus.api.common.variables;

public enum ExceptionType {
    COMMON_EXCEPTION(1),
    UNAUTHENTICATED_EXCEPTION(2),
    UNAUTHORIZED_EXCEPTION(3);

    private final int type;

    ExceptionType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
