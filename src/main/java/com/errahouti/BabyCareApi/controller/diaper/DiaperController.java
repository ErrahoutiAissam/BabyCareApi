package com.errahouti.BabyCareApi.controller.diaper;

import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.service.DiaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaper")
public class DiaperController {

    private final DiaperService diaperService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(diaperService.getAllDiaper());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DiaperDTO createRequest){
        return ResponseEntity.ok(diaperService.createDiaper(createRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody DiaperDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(diaperService.update(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiaperById(@PathVariable("id") Long id){
        return ResponseEntity.ok(diaperService.getDiaperById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        diaperService.deleteDiaper(id);
        return ResponseEntity.ok("Diaper deleted successfully");
    }
}
