package com.example.feedback.exception;

public class ReviewServiceException extends RuntimeException {

    public ReviewServiceException(String message) {
        super(message);
    }

    public ReviewServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
