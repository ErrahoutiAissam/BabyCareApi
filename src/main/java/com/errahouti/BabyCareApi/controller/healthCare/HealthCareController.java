package com.errahouti.BabyCareApi.controller.healthCare;


import com.errahouti.BabyCareApi.dto.healthCare.HealthCareDTO;
import com.errahouti.BabyCareApi.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/healthCare")

public class HealthCareController {

    private final HealthService healthService;

    @GetMapping
    public ResponseEntity<?> getAllHealthCares(){

        return ResponseEntity.ok(healthService.getAllHealthCares());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createHealth(@RequestBody HealthCareDTO createRequest){
        return ResponseEntity.ok(healthService.createHealthCareReminder(createRequest));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateHealthCare(
            @RequestBody HealthCareDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(healthService.updateHealthCare(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHealthCare(@PathVariable("id") Long id){
        return ResponseEntity.ok(healthService.getHealthCareById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHealthCare(@PathVariable("id") Long id){
        healthService.deleteHealthCare(id);
        return ResponseEntity.ok("HealthCare Reminder deleted successfully");
    }
}
