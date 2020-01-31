package com.hillel.task_9.model;

import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class ErrorResponse {
    String url;
    String errorMessage;
    HttpStatus status;
}
