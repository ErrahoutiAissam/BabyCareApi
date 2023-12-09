package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.note.NoteDTO;
import com.errahouti.BabyCareApi.dto.note.NoteMapper;
import com.errahouti.BabyCareApi.exception.NoteNotFoundException;
import com.errahouti.BabyCareApi.model.Note;
import com.errahouti.BabyCareApi.repository.NoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepo NoteRepo;

    private final NoteMapper NoteMapper;


    public NoteDTO createNote(NoteDTO NoteDTO){
        return NoteMapper.toNoteDTO(NoteRepo
                .save(NoteMapper.createNote(NoteDTO)));
    }

    public NoteDTO getNoteById(Long id) {
        return NoteMapper.toNoteDTO(findNoteById(id));
    }

    public NoteDTO update(NoteDTO updateRequest, Long id) {
        Note Note = findNoteById(id);
        NoteMapper.updateNoteFromDTO(updateRequest, Note);
        Note.setId(id);

        return NoteMapper.toNoteDTO(NoteRepo.save(Note));
    }

    public List<NoteDTO> getAllNote() {
        return NoteRepo.findAll().stream()
                .map(NoteMapper::toNoteDTO).toList();
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
