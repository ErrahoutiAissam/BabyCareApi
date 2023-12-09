package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
}
