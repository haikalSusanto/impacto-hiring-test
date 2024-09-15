package com.impacto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.impacto.dto.GlobalResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse> handleNotFound(NotFoundException ex) {
        log.error("Not found error:", ex);
        GlobalResponse body = GlobalResponse.builder()
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error("Validation error:", ex);
        StringBuilder errMessages = new StringBuilder();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errMessages.append(error.getDefaultMessage()).append("; ");
        });

        GlobalResponse body = GlobalResponse.builder()
                .message(errMessages.toString())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalResponse> handleRuntimeError(RuntimeException ex) {
        log.error("Global error:", ex);
        GlobalResponse body = GlobalResponse.builder()
                .message("internal server error")
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

}
