package com.errahouti.BabyCareApi.dto.diaper;


import com.errahouti.BabyCareApi.model.DiaperType;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DiaperDTO {

    private Long id;

    private DiaperType diaperType;
}
