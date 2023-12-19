package com.errahouti.BabyCareApi.dto.nutrition;


import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.NutritionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class NutritionDTO extends ReminderDTO {
    private NutritionType nutritionType;
    private String label;
    private Long quantity;
}
