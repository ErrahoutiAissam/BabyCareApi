package com.errahouti.BabyCareApi.dto.sleep;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Sleep;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {ReminderMapper.class})
public interface SleepMapper {

    Sleep createSleep(CreateSleepDTO sleepDTO);

    SleepDTO toSleepDTO(Sleep sleep);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSleep(UpdateSleepDTO request, @MappingTarget Sleep sleep);

}
