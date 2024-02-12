package com.sb.tech.controllers.advice;

import com.sb.tech.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;

@ControllerAdvice
public class ControllerAdviceHandler {

    public static final String BAD_REQUEST = "Bad Request";
    public static final String REPAIR_NOT_FOUND = "Repair not found";
    public static final String VALIDATION_ERROR = "Validation error";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<StandardError> notFounException(NotFoundException e, HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(),
                REPAIR_NOT_FOUND, e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationError> validationException(MethodArgumentNotValidException e, HttpServletRequest http){
        ValidationError error = new ValidationError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                VALIDATION_ERROR, e.getTitleMessageCode(), http.getRequestURI());
        for (FieldError field : e.getFieldErrors()){
            error.addListErrors(field.getField(), field.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> sqlIntegrityViolationException(SQLIntegrityConstraintViolationException e,
                                                                        HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                BAD_REQUEST, e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e,
                                                                        HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                BAD_REQUEST, e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
                                                                         HttpServletRequest http){
        StandardError error = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(),
                BAD_REQUEST, e.getMessage(), http.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
