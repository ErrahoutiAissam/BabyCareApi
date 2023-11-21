package com.errahouti.BabyCareApi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sleep extends Reminder{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private int awakenings;
    @Enumerated(EnumType.STRING)
    private SleepType sleepType;

    // the sleep quality is related to awakenings.



}
