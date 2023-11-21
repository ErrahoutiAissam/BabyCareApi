package com.errahouti.BabyCareApi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private int age;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private double weight;
    private double height;

    @OneToMany
    private List<Nutrition> nutritionReminders;
    @OneToMany
    private List<Diaper> diaperReminders ;
    @OneToMany
    private List<Activity> activityReminders;
    @OneToMany
    private List<HealthCare> healthCareReminders;
    @OneToMany
    private List<Sleep> sleepReminders;


}
