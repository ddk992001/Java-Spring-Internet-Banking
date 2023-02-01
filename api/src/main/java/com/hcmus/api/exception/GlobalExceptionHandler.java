package com.hcmus.api.exception;

import com.hcmus.api.common.variables.ExceptionType;
import com.hcmus.api.common.variables.FailedOperation;
import com.hcmus.api.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<Response> handleGenericException(GenericException exception) {
        FailedOperation error = exception.getError();

        if (exception.getType().getType() == ExceptionType.COMMON_EXCEPTION.getType())
            return new ResponseEntity<>(new Response(error.getMessage(), error.getCode(), error.isStatus()), HttpStatus.BAD_REQUEST);
        else if (exception.getType().getType() == ExceptionType.UNAUTHENTICATED_EXCEPTION.getType())
            return new ResponseEntity<>(new Response(error.getMessage(), error.getCode(), error.isStatus()), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(new Response(error.getMessage(), error.getCode(), error.isStatus()), HttpStatus.FORBIDDEN);
    }
}
