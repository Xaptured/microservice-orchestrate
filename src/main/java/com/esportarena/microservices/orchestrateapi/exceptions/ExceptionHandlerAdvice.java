package com.esportarena.microservices.orchestrateapi.exceptions;

import com.esportarena.microservices.orchestrateapi.models.ExceptionBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(LANEmailException.class)
    public ResponseEntity<ExceptionBody> handleValidationException(LANEmailException exception) {
        LOGGER.error("Exception occurred during validation: " + exception.getMessage());
        ExceptionBody exceptionBody = new ExceptionBody(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionBody);
    }
}
