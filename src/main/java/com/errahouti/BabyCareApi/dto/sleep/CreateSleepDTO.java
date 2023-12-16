package com.errahouti.BabyCareApi.dto.sleep;

import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.SleepType;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CreateSleepDTO extends ReminderDTO {

    private Date startDate;
    private Date endDate;
    private int awakenings;
    private SleepType sleepType;


}
