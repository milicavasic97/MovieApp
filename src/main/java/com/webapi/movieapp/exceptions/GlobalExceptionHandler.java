package com.webapi.movieapp.exceptions;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value("${debug.response}")
    private boolean debug;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        return errorBuilder(ex, request, ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorMessage> errorBuilder(Exception ex, WebRequest request, String errorMessage,
                                                      HttpStatus status) {
        ErrorMessage message = new ErrorMessage(new Date().toString(), status.value(),
                (debug ? ex.getMessage() : ""), request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, status);
    }
}
