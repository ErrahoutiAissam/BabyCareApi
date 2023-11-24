package com.errahouti.BabyCareApi.dto.diaper;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Diaper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ReminderMapper.class})
public interface DiaperMapper {

    Diaper createDiaper(DiaperDTO diaperDTO);

    DiaperDTO toDiaperDTO(Diaper diaper);
    void updateDiaperFromDTO(DiaperDTO diaperDTO, @MappingTarget Diaper diaper);

}
