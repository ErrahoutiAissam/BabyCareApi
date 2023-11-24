package com.errahouti.BabyCareApi.controller.sleep;

import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.UpdateSleepDTO;
import com.errahouti.BabyCareApi.exception.SleepNotFoundException;
import com.errahouti.BabyCareApi.model.Sleep;
import com.errahouti.BabyCareApi.service.SleepService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sleep")
public class SleepController {

    private final SleepService sleepService;
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sleepService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSleepDTO createRequest){
        return ResponseEntity.ok(sleepService.create(createRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestBody UpdateSleepDTO updateSleepDTO,
            @PathVariable Long id
    ) throws SleepNotFoundException {
        sleepService.update(updateSleepDTO, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws SleepNotFoundException {
        sleepService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSleepById(@PathVariable("id") Long id){
        return ResponseEntity.ok(sleepService.getById(id));
    }

}
