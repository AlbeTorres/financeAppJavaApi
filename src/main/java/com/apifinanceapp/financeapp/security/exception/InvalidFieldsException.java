package com.apifinanceapp.financeapp.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidFieldsException extends ResponseStatusException {

    public InvalidFieldsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
