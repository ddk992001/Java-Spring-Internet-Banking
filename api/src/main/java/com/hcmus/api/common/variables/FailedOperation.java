package com.hcmus.api.common.variables;

public enum FailedOperation {
    LOGIN_FAILED("The username or password is invalid!", 7000),
    NOT_EXISTED_USER_TYPE("The user type does not exist in the system!", 7001),
    NOT_EXISTED_EMAIL("The email does not exist in the system!", 7002),
    NOT_EXISTED_FORGOT_PASSWORD_HISTORY_RECORD("Validation failed. Do not have any otp records for this user!", 7003),
    FORGOT_PASSWORD_OTP_VALIDATION_FAILED("Validation failed. OTP code may be incorrect or the session was expired!", 7004),
    FORGOT_PASSWORD_INVALID_RESET_TOKEN("Reset failed. This user do not have permission to reset password!", 7005),
    NOT_EXISTED_ACCOUNT("The user account does not exist in the system!", 7006),
    UNAUTHENTICATED_USER("Unauthenticated user!", 7007),
    CHANGE_PASSWORD_FAILED("Old password is incorrect!", 7008);

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
