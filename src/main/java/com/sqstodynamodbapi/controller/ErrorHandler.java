package com.sqstodynamodbapi.controller;

import com.sqstodynamodbapi.domain.model.Error;
import com.sqstodynamodbapi.domain.model.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownError(Exception e) {
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Error.builder()
                        .code(ErrorCode.INTERNAL_ERROR)
                        .message(e.getMessage())
                        .cause(e.getCause().getLocalizedMessage())
                        .build()
        );
    }

}
