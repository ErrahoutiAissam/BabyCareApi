package com.errahouti.BabyCareApi.dto.nutrition;


import com.errahouti.BabyCareApi.model.NutritionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NutritionDTO {
    private Long id;
    private NutritionType nutritionType;
    private String label;
    private Long quantity;
}
