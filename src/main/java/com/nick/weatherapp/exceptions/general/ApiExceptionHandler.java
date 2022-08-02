package com.nick.weatherapp.exceptions.general;

import com.nick.weatherapp.exceptions.InvalidApiKey;
import com.nick.weatherapp.exceptions.LocationNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/* Handles the exception when it occurs */

@ControllerAdvice
public class ApiExceptionHandler {

    // UNAUTHORISED - HttpStatus:
    @ExceptionHandler(value = {InvalidApiKey.class})                    // pass list of exceptions we are catching
    public ResponseEntity<Object> handleInvalidApiKeyException(InvalidApiKey e) {
        // 1. Create payload containing exception details
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.UNAUTHORIZED, ZonedDateTime.now(ZoneId.of("Z")));
        // 2. Return response entity
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);
    }

    // BAD REQUESTS - HttpStatus:
    @ExceptionHandler(value = {LocationNotFound.class})                    // pass list of exceptions we are catching
    public ResponseEntity<Object> handleLocationNotFoundException(LocationNotFound e) {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
