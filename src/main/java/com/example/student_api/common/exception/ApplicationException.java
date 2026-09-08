package com.example.student_api.common.exception;

public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

}
