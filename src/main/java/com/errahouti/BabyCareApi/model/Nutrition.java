package com.errahouti.BabyCareApi.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition extends Reminder {

    @Enumerated(EnumType.STRING)
    private NutritionType nutritionType;
    private String label;

    // if the type is solid --> quantity in g : ml;
    private Long quantity;


    @Override
    public String toString() {
        return "Nutrition{" +
                "id=" + getId() +
                ", nutritionType=" + nutritionType +
                ", label='" + label + '\'' +
                ", quantity=" + quantity +
                ", reminderDate" + getReminderDate() +
                ", reminderState" + getReminderState() +
                ", childId:" + getChild().getId()+
                '}';
    }
}
