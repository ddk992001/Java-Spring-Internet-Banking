package com.hcmus.api.exception;

import com.hcmus.api.common.FailedOperation;
import com.hcmus.api.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = GenericException.class)
    public ResponseEntity<Response> handleRestaurantException(GenericException exception) {
        FailedOperation error = exception.getError();
        return new ResponseEntity<>(new Response(error.getMessage(), error.getCode(), error.isStatus()), HttpStatus.BAD_REQUEST);
    }
}
