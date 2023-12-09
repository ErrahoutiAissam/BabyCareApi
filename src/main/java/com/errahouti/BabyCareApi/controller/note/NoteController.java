package com.errahouti.BabyCareApi.controller.note;


import com.errahouti.BabyCareApi.dto.note.NoteDTO;
import com.errahouti.BabyCareApi.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/note")
public class NoteController {


    private final NoteService NoteService;
    @GetMapping
    public ResponseEntity<?> getAllNote(){

        return ResponseEntity.ok(NoteService.getAllNote());
    }

    @PostMapping("/add")
    public ResponseEntity<?> createNote(@RequestBody NoteDTO createRequest){
        return ResponseEntity.ok(NoteService.createNote(createRequest));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateTip(
            @RequestBody NoteDTO updateRequest,
            @PathVariable("id") Long id)
    {
        return ResponseEntity.ok(NoteService.update(updateRequest, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTip(@PathVariable("id") Long id){
        return ResponseEntity.ok(NoteService.getNoteById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTip(@PathVariable("id") Long id){
        NoteService.deleteNote(id);
        return ResponseEntity.ok("Note deleted successfully");
    }

}
