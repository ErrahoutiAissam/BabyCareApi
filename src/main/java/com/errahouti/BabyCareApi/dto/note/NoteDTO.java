package com.errahouti.BabyCareApi.dto.note;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class NoteDTO {

    private Long id;
    private String title;
    private String content;
    private Date date;
}
