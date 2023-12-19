package com.errahouti.BabyCareApi.dto.healthCare;

import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.HealthCareType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class HealthCareDTO extends ReminderDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private HealthCareType healthCareType;
    private String notes;
}
