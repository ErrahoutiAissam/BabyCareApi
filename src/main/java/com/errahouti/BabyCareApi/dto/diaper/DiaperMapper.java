package com.errahouti.BabyCareApi.dto.diaper;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Diaper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReminderMapper.class})
public interface DiaperMapper {

    Diaper createDiaper(DiaperDTO diaperDTO);

    DiaperDTO toDiaperDTO(Diaper diaper);
}
