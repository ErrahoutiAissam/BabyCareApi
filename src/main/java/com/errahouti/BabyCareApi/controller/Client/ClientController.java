package com.errahouti.BabyCareApi.controller.Client;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class ClientController {
    @GetMapping
    public ResponseEntity<String> doSomething(){
        return ResponseEntity.ok("we are doing something");
    }
}
