package com.gitlab.emradbuba.training.mpeek.rest.exceptions;

public class UserNotFoundException extends RestException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
