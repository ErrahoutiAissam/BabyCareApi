package com.errahouti.BabyCareApi.model;

import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.dto.tips.TipsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayInfo {

    private List<ReminderDTO> todaysReminders;
    private int remindersCompleted;
    private int remindersNotCompleted;
    private TipsDTO tips;

}
