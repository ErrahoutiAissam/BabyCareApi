package com.errahouti.BabyCareApi.dto.reminder;


import com.errahouti.BabyCareApi.model.ReminderState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class ReminderDTO {

    private Long id;
    private Date reminderDate;
    private ReminderState reminderState;
}
