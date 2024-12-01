package com.exercise.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomException> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest webRequest) {
        CustomException customException = CustomException.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .timeStamp(new Date())
                .build();

        return new ResponseEntity<CustomException>(customException, HttpStatus.NOT_FOUND);

    }

}
