package com.indocyber.Controllers;


import com.indocyber.ExceptionHandling.ErrorResponse;
import com.indocyber.ExceptionHandling.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionRestController {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ObjectNotFound objectNotFound){

        ErrorResponse response = new ErrorResponse();
        response.setMessage(objectNotFound.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RuntimeException runtimeException){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(runtimeException.getMessage());
        response.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
        return new ResponseEntity<>(response,HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException methodArgumentNotValidException){

        Map<String,String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ( (FieldError) error ).getField();
                    String errorMsg  = error.getDefaultMessage();

                    errors.put(fieldName,errorMsg);
                });

        ErrorResponse response = new ErrorResponse();
        response.setMessage(errors);
        response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }
}
