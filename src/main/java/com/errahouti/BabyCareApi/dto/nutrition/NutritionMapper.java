package com.errahouti.BabyCareApi.dto.nutrition;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Nutrition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})
public interface NutritionMapper {
    NutritionDTO toNutritionDTO(Nutrition nutrition);

    Nutrition createNutrition(NutritionDTO nutritionDTO);


}
