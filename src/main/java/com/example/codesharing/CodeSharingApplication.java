package com.example.codesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CodeSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingApplication.class, args);
    }

}
