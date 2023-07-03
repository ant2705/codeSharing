package com.example.codesharing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class shareCodeController {
    @GetMapping("/sharecode")
    public String sharing(@RequestParam("text") String text){
        System.out.println(text);
        return "OK";
    }
}
