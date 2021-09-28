package com.gitlab.emradbuba.training.mpeek.rest.controller.handler;

import com.gitlab.emradbuba.training.mpeek.rest.exceptions.RestException;
import com.gitlab.emradbuba.training.mpeek.rest.exceptions.UserNotFoundRestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(UserNotFoundRestException.class)
    public ResponseEntity handleUserNotFoundRestException(UserNotFoundRestException e) {
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(RestException.class)
    public ResponseEntity handleRestException(RestException e) {
        ResponseEntity response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}