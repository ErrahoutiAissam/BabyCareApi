package com.errahouti.BabyCareApi.exception;

import lombok.Getter;

public class SleepNotFoundException extends NotFoundException{

 @Getter
    private final Long sleepId;

    public SleepNotFoundException(Long sleepId) {
        this(sleepId, "Sleep with id : " +sleepId + " not found");
    }

    public SleepNotFoundException(Long sleepId, String message) {
        super(message);
        this.sleepId = sleepId;
    }


}
