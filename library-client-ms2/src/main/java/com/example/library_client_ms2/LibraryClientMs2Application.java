package com.example.library_client_ms2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LibraryClientMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(LibraryClientMs2Application.class, args);
	}

}
