package com.eteration.simplebanking.exception;

import com.eteration.simplebanking.controller.TransactionStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<TransactionStatus> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return ResponseEntity.status(400).body(new TransactionStatus(ex.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<TransactionStatus> handleException(Exception ex) {
        return ResponseEntity.status(500).body(new TransactionStatus(ex.getMessage()));
    }
}

