package com.hcmus.api.common.variables;

public enum SuccessfulOperation {
    FORGOT_PASSWORD_VALIDATE_EMAIL_SUCCESSFULLY("OTP code was sent. Please check your email and verify!", 5000),
    FORGOT_PASSWORD_VALIDATE_OTP_SUCCESSFULLY("Validation OTP successfully!", 5001),
    FORGOT_PASSWORD_RESET_PASSWORD_SUCCESSFULLY("Reset successfully. Please back to login screen and sign up!", 5002),
    CHANGE_PASSWORD_SUCCESSFULLY("Change password successfully!", 5003);

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
