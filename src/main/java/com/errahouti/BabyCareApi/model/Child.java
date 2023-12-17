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

    @ManyToOne
    private User parent;

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Sleep> sleepReminders;
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Activity> activityReminders;
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Diaper> diaperReminders;
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<HealthCare> healthCareReminders;
    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL)
    private List<Nutrition> NutritionReminders;

}
