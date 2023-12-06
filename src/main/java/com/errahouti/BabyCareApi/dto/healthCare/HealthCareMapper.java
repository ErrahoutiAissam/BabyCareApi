package com.errahouti.BabyCareApi.dto.healthCare;

import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.HealthCare;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})

public interface HealthCareMapper {

    HealthCare createHealthCare(HealthCareDTO healthCareDTO);
    HealthCareDTO toHealthCareDTO(HealthCare healthCare);

    void updateHealthCareFromDTO(HealthCareDTO healthCareDTO, @MappingTarget HealthCare healthCare);
}
