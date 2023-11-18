package com.errahouti.BabyCareApi.controller.Client;


import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demo")
public class ClientController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> doSomething(){
        return ResponseEntity.ok("we are doing something");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.userList());
    }
}
