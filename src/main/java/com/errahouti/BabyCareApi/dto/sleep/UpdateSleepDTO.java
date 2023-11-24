package com.errahouti.BabyCareApi.dto.sleep;

import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.SleepType;

import java.util.Date;

public class UpdateSleepDTO extends ReminderDTO {
    private Date startDate;
    private Date endDate;
    private int awakenings;
    private SleepType sleepType;
}
