package com.errahouti.BabyCareApi.dto.sleep;

import com.errahouti.BabyCareApi.model.SleepType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class SleepDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private int awakenings;
    private SleepType sleepType;
}
