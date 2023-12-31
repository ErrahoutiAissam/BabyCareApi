package com.errahouti.BabyCareApi.controller.child;


import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.model.ChildProgress;
import com.errahouti.BabyCareApi.repository.ChildProgressRepo;
import com.errahouti.BabyCareApi.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/children")
public class ChildController {

    private final ChildService childService;
    private final ReminderService reminderService;
    private final NutritionService nutritionService;
    private final DiaperService diaperService;
    private final SleepService sleepService;
    private final HealthService healthService;
    private final ChildProgressRepo childProgressRepo;


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

    @GetMapping("/{id}/reminder")
    public ResponseEntity<?> getChildReminders(@PathVariable("id") Long id){
        return ResponseEntity.ok(reminderService.getChildReminders(id));
    }

    @GetMapping("/{id}/nutritions")
    public ResponseEntity<?> getChildNutritions(@PathVariable("id") Long id){
        return  ResponseEntity.ok(nutritionService.getChildNutritions(id));
    }
    @GetMapping("/{id}/sleep")
    public ResponseEntity<?> getChildSleepReminder(@PathVariable("id") Long id){
        return  ResponseEntity.ok(sleepService.getChildSleepReminder(id));
    }
    @GetMapping("/{id}/healthCare")
    public ResponseEntity<?> getChildHealthCareReminder(@PathVariable("id") Long id){
        return  ResponseEntity.ok(healthService.getChildHealthCare(id));
    }

    @GetMapping("/{id}/diaper")
    public ResponseEntity<?> getChildDiapers(@PathVariable("id") Long id){
        return ResponseEntity.ok(diaperService.getChildDiapers(id));
    }

    @GetMapping("/{id}/progress/weight")
    public ResponseEntity<Map<Integer, Double>> getChildWeightProgress(@PathVariable Long id) {
        List<ChildProgress> progressList = childProgressRepo.findByChild_Id(id);

        Map<Integer, Double> weightProgressMap = progressList.stream()
                .flatMap(progress -> progress.getGrowthData().entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));

        return ResponseEntity.ok(weightProgressMap);
    }



}
