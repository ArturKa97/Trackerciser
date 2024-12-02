package com.exercise.project.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.net.URI;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class CustomErrorResponse implements ErrorResponse {

    private String type;
    private String title;
    private HttpStatusCode statusCode;
    private String detail;
    private List<String> details;
    private Date timeStamp;

    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, detail);
        problemDetail.setTitle(title);
        problemDetail.setType(URI.create(type));
        problemDetail.setProperty("details", details);
        problemDetail.setProperty("timestamp", timeStamp);
        return problemDetail;
    }
}
