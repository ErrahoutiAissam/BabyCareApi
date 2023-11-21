package com.errahouti.BabyCareApi.dto.tips;


import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Tips;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})

public interface TipsMapper {

    @Mapping(target = "parentList", ignore = true)
    Tips createTips(TipsDTO tipsDTO);

    @Mapping(target = "parentList", ignore = true)
    TipsDTO toTipsDTO(Tips tips);
}
