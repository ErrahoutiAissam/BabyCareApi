package com.errahouti.BabyCareApi.dto.tips;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class TipsDTO {
    private Long id;
    private String category;
    private String target;
    private String description;

}
