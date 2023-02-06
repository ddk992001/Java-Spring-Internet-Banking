package com.hcmus.api.exception;

import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;

public class GenericException extends RuntimeException {
    private final FailedOperation error;
    private final ExceptionType type;

    public GenericException(FailedOperation error, ExceptionType type) {
        this.error = error;
        this.type = type;
    }

    public FailedOperation getError() {
        return this.error;
    }

    public ExceptionType getType() {
        return this.type;
    }
}
