package com.errahouti.BabyCareApi.controller.parent;


import com.errahouti.BabyCareApi.dto.user.UserDTO;
import com.errahouti.BabyCareApi.exception.EmailAlreadyExistsException;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class ParentController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getParent(@PathVariable("id") Long id) throws NotFoundException {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<?> updateParent(
            @PathVariable("id") Long id,
            @RequestBody UserDTO request
    ){
       return ResponseEntity.ok(userService.updateUser(request,id));
    }

    @SneakyThrows
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParent(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }


    @SneakyThrows
    @PostMapping("/{id}/addChild/{childId}")
    public ResponseEntity<?> addChildToParent(
            @PathVariable("id") Long parentId,
            @PathVariable("childId") Long childId
    ){
        userService.addChild(childId, parentId);
        return ResponseEntity.ok("Child added Successfully");

    }


}
