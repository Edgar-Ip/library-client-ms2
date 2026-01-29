package com.example.library_client_ms2.controller;


import com.example.library_client_ms2.service.LibraryClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/library")
public class LibraryClientController {

    private final LibraryClientService service;

    public LibraryClientController(LibraryClientService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<?> getUsers(
            @RequestHeader(value = "MyFlag", required = false) Boolean flag
    ) {
        return service.getUsers(flag);
    }

}
