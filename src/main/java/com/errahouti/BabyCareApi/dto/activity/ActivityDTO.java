package com.errahouti.BabyCareApi.dto.activity;


import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.ActivityType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class ActivityDTO extends ReminderDTO {
    private Long id;

    private ActivityType activityType;

    private String notes;
}
