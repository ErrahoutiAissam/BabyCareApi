package com.errahouti.BabyCareApi.controller.tips;


import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.dto.tips.TipsDTO;
import com.errahouti.BabyCareApi.service.TipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tips")
public class TipsController {


    private final TipsService tipsService;
    @GetMapping
    public ResponseEntity<?> getAllTips(){

        return ResponseEntity.ok(tipsService.getAllTips());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createTips(@RequestBody TipsDTO createRequest){
        return ResponseEntity.ok(tipsService.createTip(createRequest));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateTip(
            @RequestBody TipsDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(tipsService.updateTip(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTip(@PathVariable("id") Long id){
        return ResponseEntity.ok(tipsService.getTipById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTip(@PathVariable("id") Long id){
        tipsService.deleteTip(id);
        return ResponseEntity.ok("Tip deleted successfully");
    }

    @GetMapping("/random")
    public ResponseEntity<?> getRandomTip(){
        System.out.println(tipsService.getRandomTip());
        return ResponseEntity.ok(tipsService.getRandomTip().getDescription());
    }

}
