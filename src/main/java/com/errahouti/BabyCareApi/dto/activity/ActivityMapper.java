package com.errahouti.BabyCareApi.dto.activity;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})
public interface ActivityMapper {
    @Mapping(target = "childId", ignore = true)
    ActivityDTO toActivityDTO(Activity activity);
    @Mapping(target = "child", ignore = true)
    Activity createActivity(ActivityDTO activityDTO);
    @Mapping(target = "child", ignore = true)
    void updateFromDTO(ActivityDTO activityDTO, @MappingTarget Activity activity);

}
