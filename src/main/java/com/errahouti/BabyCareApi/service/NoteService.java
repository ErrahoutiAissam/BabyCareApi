package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.note.CreateNoteRequest;
import com.errahouti.BabyCareApi.dto.note.NoteDTO;
import com.errahouti.BabyCareApi.dto.note.NoteMapper;
import com.errahouti.BabyCareApi.exception.NoteNotFoundException;
import com.errahouti.BabyCareApi.model.Note;
import com.errahouti.BabyCareApi.repository.NoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepo NoteRepo;

    private final NoteMapper noteMapper;


    public NoteDTO createNote(CreateNoteRequest noteDTO){
        Note note= noteMapper.createNote(noteDTO);
        note.setDate(new Date());
        return noteMapper.toNoteDTO(NoteRepo
                .save(note));
    }

    public NoteDTO getNoteById(Long id) {
        return noteMapper.toNoteDTO(findNoteById(id));
    }

    public NoteDTO update(NoteDTO updateRequest, Long id) {
        Note Note = findNoteById(id);
        noteMapper.updateNoteFromDTO(updateRequest, Note);
        Note.setDate(new Date());
        Note.setId(id);

        return noteMapper.toNoteDTO(NoteRepo.save(Note));
    }

    public List<NoteDTO> getAllNote() {
        return NoteRepo.findAll().stream()
                .map(noteMapper::toNoteDTO).toList();
    }


    public void deleteNote(Long id) {
        Note Note = findNoteById(id);
        NoteRepo.delete(Note);
    }


    private Note findNoteById(Long id) {
        return NoteRepo.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }
}
