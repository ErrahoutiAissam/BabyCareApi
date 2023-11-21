package com.errahouti.BabyCareApi.dto.activity;


import com.errahouti.BabyCareApi.model.ActivityType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ActivityDTO {
    private Long id;

    private ActivityType activityType;

    private String notes;
}
