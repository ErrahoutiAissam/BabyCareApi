package com.errahouti.BabyCareApi.dto.child;


import com.errahouti.BabyCareApi.model.Child;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChildMapper {


    @Mapping(target = "nutritionReminders", ignore = true)
    @Mapping(target = "diaperReminders", ignore = true)
    @Mapping(target = "activityReminders", ignore = true)
    @Mapping(target = "healthCareReminders", ignore = true)
    @Mapping(target = "sleepReminders", source = "sleepReminders")
    ChildDTO toChildDTO(Child child);

    @Mapping(target = "nutritionReminders", ignore = true)
    @Mapping(target = "diaperReminders", ignore = true)
    @Mapping(target = "activityReminders", ignore = true)
    @Mapping(target = "healthCareReminders", ignore = true)
    @Mapping(target = "sleepReminders", source = "sleepReminders")
    Child toChild(ChildDTO childDTO);

    @Mapping(target = "id", ignore = true)
    void update(ChildDTO childDTO, @MappingTarget Child child);
}
