package com.errahouti.BabyCareApi.dto.reminder;


import com.errahouti.BabyCareApi.model.Reminder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReminderMapper {


    @Mapping(target = "reminderDate", source = "reminderDTO.reminderDate")
    Reminder createReminder(ReminderDTO reminderDTO);

    @Mapping(target = "reminderDate", source = "reminder.reminderDate")
    ReminderDTO toReminderDTO(Reminder reminder);
}
