package com.hcmus.api.common;

public class Response {
    private final String message;
    private final int code;
    private final boolean status;

    public Response(String message, int code, boolean status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public boolean isStatus() {
        return status;
    }
}
