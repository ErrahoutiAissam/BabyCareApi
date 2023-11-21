package com.errahouti.BabyCareApi.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Nutrition extends Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NutritionType nutritionType;
    private String label;

    // if the type is solid --> quantity in g : ml;
    private Long quantity;


}
