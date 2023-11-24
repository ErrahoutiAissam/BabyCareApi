package com.errahouti.BabyCareApi.exception;

import lombok.Getter;

public class ActivityNotFoundException extends NotFoundException{

 @Getter
    private final Long id;

    public ActivityNotFoundException(Long id) {
        this(id, "Activity with id : " +id + " not found");
    }

    public ActivityNotFoundException(Long id, String message) {
        super(message);
        this.id = id;
    }


}
