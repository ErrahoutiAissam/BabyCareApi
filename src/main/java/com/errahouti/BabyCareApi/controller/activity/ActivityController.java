package com.errahouti.BabyCareApi.controller.activity;

import com.errahouti.BabyCareApi.dto.activity.ActivityDTO;
import com.errahouti.BabyCareApi.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity")
public class ActivityController {

    private final ActivityService ActivityService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(ActivityService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActivityDTO createRequest){
        return ResponseEntity.ok(ActivityService.createActivity(createRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody ActivityDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(ActivityService.update(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ActivityService.getActivityById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        ActivityService.deleteActivity(id);
        return ResponseEntity.ok("Activity deleted successfully");
    }
}
