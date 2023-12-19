package com.errahouti.BabyCareApi.dto.note;

import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {ReminderMapper.class})

public interface NoteMapper {

    Note createNote(CreateNoteRequest NoteDTO);
    NoteDTO toNoteDTO(Note Note);

    void updateNoteFromDTO(NoteDTO NoteDTO, @MappingTarget Note Note);
}
