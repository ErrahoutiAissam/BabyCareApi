package com.errahouti.BabyCareApi.controller.nutrition;


import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.service.NutritionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nutrition")
@Slf4j
public class NutritionController {

    private final NutritionService nutritionService;

    @GetMapping
    public ResponseEntity<?> getAllNutrition(){
        return ResponseEntity.ok(nutritionService.getAllNutrition());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNutrition(@RequestBody NutritionDTO createRequest){
        return ResponseEntity.ok(nutritionService.createNutrition(createRequest));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateNutrition(
            @RequestBody NutritionDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(nutritionService.updateNutrition(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNutrition(@PathVariable("id") Long id){
        return ResponseEntity.ok(nutritionService.getNutritionById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNutrition(@PathVariable("id") Long id){
        nutritionService.deleteNutrition(id);
        return ResponseEntity.ok("Nutrition deleted successfully");
    }

}
