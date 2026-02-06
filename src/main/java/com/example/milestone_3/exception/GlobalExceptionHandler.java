package com.example.milestone_3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle resource not found (Product, Order, etc.)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(ResourceNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Handle insufficient stock
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Map<String, String>> handleStock(InsufficientStockException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle product already exists
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleProductAlreadyExists(ProductAlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(HttpStatus.CONFLICT.value()));
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Handle invalid quantity
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<Map<String, String>> handleInvalidQuantity(InvalidQuantityException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        Map<String, String> error = new HashMap<>();
        error.put("error", String.join("; ", errors));
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
