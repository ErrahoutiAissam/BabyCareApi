package com.errahouti.BabyCareApi.controller.child;


import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.service.ChildService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/children")
public class ChildController {

    private final ChildService childService;


    @GetMapping
    public ResponseEntity<?> getAllChildren(){
        return ResponseEntity.ok(childService.getAllChildren());
    }

    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<?> getChild(@PathVariable("id") Long id){
        return ResponseEntity.ok(childService.getChildById(id));

    }

    @PostMapping("/add")
    public ResponseEntity<?> createChild(
            @RequestBody ChildDTO childDTO){
       return ResponseEntity.ok(childService.createChild(childDTO));
    }

    @SneakyThrows
    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateChild(
            @PathVariable("id") Long id,
            @RequestBody ChildDTO updateRequest
            ){
        return ResponseEntity.ok(childService.updateChild(updateRequest, id));
    }

    @SneakyThrows
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteChild( @PathVariable("id") Long id){
        childService.deleteChild(id);
        return ResponseEntity.ok("child Deleted Successfully");
    }


}
