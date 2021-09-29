package com.gitlab.emradbuba.training.mpeek.rest.controller.handler;

import com.gitlab.emradbuba.training.mpeek.rest.exceptions.RestException;
import com.gitlab.emradbuba.training.mpeek.rest.exceptions.UserNotFoundRestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(UserNotFoundRestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestApiResponse handleUserNotFoundRestException(UserNotFoundRestException e) {
        return new RestApiResponse("No user returned from API", e.getMessage());
    }

    @ExceptionHandler(RestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestApiResponse handleRestException(RestException e) {
        return new RestApiResponse("Request processing failed", e.getMessage());
    }
}