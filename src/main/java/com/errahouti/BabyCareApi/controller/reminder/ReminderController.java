package com.errahouti.BabyCareApi.controller.reminder;


import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reminder")
public class ReminderController {
    private final ReminderService reminderService;

    @GetMapping
    public ResponseEntity<?> getAllReminders(){
        return ResponseEntity.ok(reminderService.getAllReminders());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createReminder(@RequestBody ReminderDTO createRequest){
        return ResponseEntity.ok(reminderService.createReminder(createRequest));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateReminder(
            @RequestBody ReminderDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(reminderService.updateReminder(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReminder(@PathVariable("id") Long id){
        return ResponseEntity.ok(reminderService.getReminderById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReminder(@PathVariable("id") Long id){
        reminderService.deleteReminder(id);
        return ResponseEntity.ok("Reminder deleted successfully");
    }

    @GetMapping("/today")
    public ResponseEntity<?> getTodayReminders(){
        return ResponseEntity.ok(reminderService.getTodayReminders());
    }
    @GetMapping("/today/completed")
    public ResponseEntity<?> getTodayCompletedReminders(){
        return ResponseEntity.ok(reminderService.getTodayRemindersCompleted());
    }
    @GetMapping("/today/upcoming")
    public ResponseEntity<?> getTodayUpcomingReminders(){
        return ResponseEntity.ok(reminderService.getTodayUpcomingReminders());
    }

}
