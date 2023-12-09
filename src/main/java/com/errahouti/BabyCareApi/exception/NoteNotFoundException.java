package com.errahouti.BabyCareApi.exception;

import lombok.Getter;

public class NoteNotFoundException extends NotFoundException{

 @Getter
    private final Long NoteId;

    public NoteNotFoundException(Long NoteId) {
        this(NoteId, "Note with id : " +NoteId + " not found");
    }

    public NoteNotFoundException(Long NoteId, String message) {
        super(message);
        this.NoteId = NoteId;
    }


}
