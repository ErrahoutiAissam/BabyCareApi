package com.errahouti.BabyCareApi.dto.sleep;

import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateSleepDTO {

    private Date startDate;
    private Date endDate;
}
