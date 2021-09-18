package com.gitlab.emradbuba.training.mpeek.rest.exceptions;

public class RestException extends RuntimeException {
    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }
}
