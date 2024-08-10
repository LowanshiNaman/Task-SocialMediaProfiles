package com.example.CloudColon.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorBody> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThemeNotFoundException.class)
    public ResponseEntity<ErrorBody> handleThemeNotFoundException(ThemeNotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(ex.getMessage(), HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
