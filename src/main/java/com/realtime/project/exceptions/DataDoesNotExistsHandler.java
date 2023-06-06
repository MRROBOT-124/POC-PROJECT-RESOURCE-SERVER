package com.realtime.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

public class DataDoesNotExistsHandler {

    @ExceptionHandler(value = {DataDoesNotExistsException.class})
    public ResponseEntity<ExceptionFormat> handleUniqueConstraintExceptions(Exception ex) {
        return new ResponseEntity<>(ExceptionFormat.builder().message(ex.getMessage()).httpStatus(HttpStatus.BAD_REQUEST).dateTime(ZonedDateTime.now()).build(), HttpStatus.BAD_REQUEST);
    }
}
