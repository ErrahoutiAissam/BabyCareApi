package com.errahouti.BabyCareApi.dto.note;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NoteDTO {

    private Long id;
    private String title;
    private String content;
    private String date;
}
