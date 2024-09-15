package com.impacto.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
