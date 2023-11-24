package com.errahouti.BabyCareApi.dto.diaper;


import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.model.DiaperType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class DiaperDTO extends ReminderDTO {

    private Long id;

    private DiaperType diaperType;
}
