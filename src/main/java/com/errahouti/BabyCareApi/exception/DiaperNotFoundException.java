package com.errahouti.BabyCareApi.exception;

import lombok.Getter;

public class DiaperNotFoundException extends NotFoundException{

 @Getter
    private final Long diaperId;

    public DiaperNotFoundException(Long diaperId) {
        this(diaperId, "Diaper with id : " +diaperId + " not found");
    }

    public DiaperNotFoundException(Long diaperId, String message) {
        super(message);
        this.diaperId = diaperId;
    }


}
