package com.errahouti.BabyCareApi.dto.child;


import com.errahouti.BabyCareApi.model.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildMapper {


    @Mapping(target = "nutritionReminders", ignore = true)
    @Mapping(target = "diaperReminders", ignore = true)
    @Mapping(target = "activityReminders", ignore = true)
    @Mapping(target = "healthCareReminders", ignore = true)
    ChildDTO toChildDTO(Child child);

    @Mapping(target = "nutritionReminders", ignore = true)
    @Mapping(target = "diaperReminders", ignore = true)
    @Mapping(target = "activityReminders", ignore = true)
    @Mapping(target = "healthCareReminders", ignore = true)
    Child toChild(ChildDTO childDTO);
}
