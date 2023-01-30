package com.hcmus.api.exception;

import com.hcmus.api.common.FailedOperation;

public class GenericException extends Exception {
    private final FailedOperation error;

    public GenericException(FailedOperation error) {
        this.error = error;
    }

    public FailedOperation getError() {
        return this.error;
    }
}
