package com.exercise.project.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.net.URI;
import java.util.Date;

@Builder
@Data
public class CustomErrorResponse implements ErrorResponse {

    private String type;
    private String title;
    private HttpStatusCode statusCode;
    private String message;
    private Date timeStamp;

    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, message);
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(type));
        problemDetail.setProperty("timestamp", timeStamp);
        return problemDetail;
    }
}
