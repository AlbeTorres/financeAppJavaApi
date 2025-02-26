package com.apifinanceapp.financeapp.security.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ResponseException extends ResponseStatusException {

    public ResponseException(HttpStatusCode status, String message) {
        super(status, message);
    }

}
