package com.errahouti.BabyCareApi.dto.sleep;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Sleep;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReminderMapper.class})
public interface SleepMapper {

    Sleep createSleep(SleepDTO sleepDTO);

    SleepDTO toSleepDTO(Sleep sleep);
}
