package com.exercise.project.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .type("https://docs.oracle.com/javaee/6/api/javax/persistence/EntityNotFoundException.html")
                .title("Entity was not found")
                .statusCode(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .timeStamp(new Date())
                .build();

        ProblemDetail problemDetail = customErrorResponse.getBody();

        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.NOT_FOUND);

    }

}
