package com.exercise.project.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .type("https://docs.oracle.com/javaee/6/api/javax/persistence/EntityNotFoundException.html")
                .title("Entity was not found")
                .statusCode(HttpStatus.NOT_FOUND)
                .detail(ex.getMessage())
                .timeStamp(new Date())
                .build();
        ProblemDetail problemDetail = customErrorResponse.getBody();
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemDetail> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .type("https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html")
                .title("Invalid data provided")
                .statusCode(HttpStatus.BAD_REQUEST)
                .detail(ex.getMessage())
                .timeStamp(new Date())
                .build();
        ProblemDetail problemDetail = customErrorResponse.getBody();
        return new ResponseEntity<ProblemDetail>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(Exception ex, WebRequest webRequest) {
        List<String> errorMessages;
        String type;
        if (ex instanceof MethodArgumentNotValidException methodArgumentNotValidException) {
            errorMessages = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                    .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                    .toList();
            type = "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/MethodArgumentNotValidException.html";
        } else if (ex instanceof ConstraintViolationException constraintViolationException) {
            errorMessages = constraintViolationException.getConstraintViolations().stream()
                    .map(violation -> String.format(
                            "Field '%s': %s",
                            violation.getPropertyPath(),
                            violation.getMessage()))
                    .toList();
            type = "https://docs.oracle.com/javaee/7/api/javax/validation/ConstraintViolationException.html";
        } else {
            errorMessages = List.of("Unexpected validation error occurred.");
            type = "https://docs.oracle.com/javase/8/docs/api/javax/xml/bind/ValidationException.html";
        }
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .type(type)
                .title("Validation failed")
                .statusCode(HttpStatus.BAD_REQUEST)
                .detail("One or more values provided are invalid")
                .details(errorMessages)
                .timeStamp(new Date())
                .build();
        ProblemDetail problemDetail = customErrorResponse.getBody();
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnhandledDefaultExceptions(Exception ex, WebRequest webRequest) {
        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .type("https://docs.apigee.com/api-platform/troubleshoot/runtime/500-internal-server-error-backend-server")
                .title("Internal Server Error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .detail("An unexpected error occurred. Please try again later.")
                .timeStamp(new Date())
                .build();
        ProblemDetail problemDetail = customErrorResponse.getBody();
        return new ResponseEntity<>(problemDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
