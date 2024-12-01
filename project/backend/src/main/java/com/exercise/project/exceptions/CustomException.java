package com.exercise.project.exceptions;

import lombok.Builder;

import java.util.Date;

@Builder
public record CustomException(Integer statusCode,
                              String message,
                              Date timeStamp) {

}
