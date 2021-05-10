package com.aegon.calculator.exception;

import com.aegon.calculator.exception.response.DivideByZeroException;
import com.aegon.calculator.exception.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

    @ResponseStatus(HttpStatus.LOOP_DETECTED)
    @ExceptionHandler(DivideByZeroException.class)
    public ResponseEntity<Response> handleNotFoundException(DivideByZeroException exception) {
        return ResponseEntity.status(HttpStatus.LOOP_DETECTED).body(new Response(exception.getMessage()));
    }
}
