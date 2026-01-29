package com.example.library_client_ms2.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException{

        private final HttpStatus status;

        public BusinessException(HttpStatus status, String message) {
            super(message);
            this.status = status;
        }

        public HttpStatus getStatus() {
            return status;
        }
    }

