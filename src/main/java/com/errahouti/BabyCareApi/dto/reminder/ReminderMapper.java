package com.errahouti.BabyCareApi.dto.reminder;


import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.model.Nutrition;
import com.errahouti.BabyCareApi.model.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReminderMapper {


    @Mapping(target = "reminderDate", source = "reminderDTO.reminderDate")
    Reminder createReminder(ReminderDTO reminderDTO);

    @Mapping(target = "reminderDate", source = "reminder.reminderDate")
    ReminderDTO toReminderDTO(Reminder reminder);

   // @Mapping(target = "reminderDate", source = "reminder.reminderDate")
    void updateReminderFromDTO(ReminderDTO reminderDTO, @MappingTarget Reminder reminder);

}
