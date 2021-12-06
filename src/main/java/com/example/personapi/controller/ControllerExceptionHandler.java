package com.example.personapi.controller;

import com.example.personapi.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public void handlePersonNotFound(PersonNotFoundException ex) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
