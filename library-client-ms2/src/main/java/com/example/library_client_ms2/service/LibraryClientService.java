package com.example.library_client_ms2.service;


import com.example.library_client_ms2.client.LibraryFeignClient;
import com.example.library_client_ms2.constants.ClientConstants;
import com.example.library_client_ms2.exception.BusinessException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class LibraryClientService {

    private final LibraryFeignClient client;

    public LibraryClientService(LibraryFeignClient client) {
        this.client = client;
    }

    @CircuitBreaker(
            name = "libraryService",
            fallbackMethod = "fallbackUsers"
    )
    public List<?> getUsers(Boolean flag) {
        return client.getUsers(flag);
    }

    public List<?> fallbackUsers(Boolean flag, Throwable ex) {

        if (isTimeout(ex)) {
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST,
                    ClientConstants.ERROR_TIMEOUT
            );
        }

        throw new BusinessException(
                HttpStatus.BAD_REQUEST,
                ClientConstants.ERROR_SERVICE_DOWN
        );
    }

    private boolean isTimeout(Throwable ex) {

        if (ex instanceof TimeoutException) {
            return true;
        }

        Throwable cause = ex;
        while (cause != null) {
            if (cause instanceof SocketTimeoutException) {
                return true;
            }
            cause = cause.getCause();
        }

        return false;
    }
}