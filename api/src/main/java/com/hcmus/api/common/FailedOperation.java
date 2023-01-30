package com.hcmus.api.common;

public enum FailedOperation {
    LOGIN_FAILED("The username or password is invalid!", 7000);

    private final String message;
    private final int code;
    private final boolean status = false;

    FailedOperation(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean isStatus() {
        return this.status;
    }
}
