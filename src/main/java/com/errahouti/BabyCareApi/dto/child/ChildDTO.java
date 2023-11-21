package com.errahouti.BabyCareApi.dto.child;


import com.errahouti.BabyCareApi.model.*;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class ChildDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Date birthDate;
    private Gender gender;
    private double weight;
    private double height;
    private List<Nutrition> nutritionReminders;
    private List<Diaper> diaperReminders ;
    private List<Activity> activityReminders;
    private List<HealthCare> healthCareReminders;


}
