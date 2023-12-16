package com.errahouti.BabyCareApi.controller.todayInfo;


import com.errahouti.BabyCareApi.model.TodayInfo;
import com.errahouti.BabyCareApi.service.ReminderService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/children")
public class TodayInfoController {

    private final ReminderService reminderService;

    @GetMapping("/{childId}/todayInfo")
    public ResponseEntity<TodayInfo> getTodayInfo(@PathVariable Long childId) {
        TodayInfo todayInfo = reminderService.getTodayInfo(childId);
        return ResponseEntity.ok(todayInfo);
    }
}