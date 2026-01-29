package com.example.library_client_ms2.client;

import com.example.library_client_ms2.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "library-ms1",
        url = "http://localhost:8081",
        configuration = FeignConfig.class
)
public interface LibraryFeignClient {

    @GetMapping("/library/users")
    List<Object> getUsers(
            @RequestHeader(value = "MyFlag", required = false) Boolean delay
    );
}


