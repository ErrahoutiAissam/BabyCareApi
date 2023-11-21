package com.errahouti.BabyCareApi.dto.tips;

import com.errahouti.BabyCareApi.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TipsDTO {
    private Long id;
    private String category;
    private String target;
    private String description;
    private List<User> parentList;

}
