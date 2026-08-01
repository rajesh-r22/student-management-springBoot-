package com.example.student_api.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;       // e.g. "Not Found", "Bad Request"
    private String message;     // human-readable summary
    private String path;        // which endpoint failed
    private Map<String, String> fieldErrors; // only populated for validation failures

}
