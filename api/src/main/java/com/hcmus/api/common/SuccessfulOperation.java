package com.hcmus.api.common;

public enum SuccessfulOperation {
    LOGIN_SUCCESSFULLY("Login successfully!", 5000);

    private final String message;
    private final int code;
    private final boolean status = true;

    SuccessfulOperation(String message, int code) {
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
