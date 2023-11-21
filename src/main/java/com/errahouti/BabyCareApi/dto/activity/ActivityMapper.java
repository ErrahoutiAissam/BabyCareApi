package com.errahouti.BabyCareApi.dto.activity;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})
public interface ActivityMapper {
    ActivityDTO toActivityDTO(Activity activity);

    Activity createActivity(ActivityDTO activityDTO);

}
