package com.learning.locationmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
        ResponseEntity<List<ErrorModel>> responseEntity =
                new ResponseEntity<>(businessException.getErrorList(), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
