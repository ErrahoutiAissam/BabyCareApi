package com.errahouti.BabyCareApi.dto.note;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateNoteRequest {
    private String title;
    private String content;
}
