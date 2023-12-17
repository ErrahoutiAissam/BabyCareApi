package com.errahouti.BabyCareApi.dto.healthCare;

import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.HealthCare;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})

public interface HealthCareMapper {

    @Mapping(target = "child", ignore = true)
    HealthCare createHealthCare(HealthCareDTO healthCareDTO);
    @Mapping(target = "childId", ignore = true)
    HealthCareDTO toHealthCareDTO(HealthCare healthCare);

    @Mapping(target = "child", ignore = true)
    void updateHealthCareFromDTO(HealthCareDTO healthCareDTO, @MappingTarget HealthCare healthCare);
}
