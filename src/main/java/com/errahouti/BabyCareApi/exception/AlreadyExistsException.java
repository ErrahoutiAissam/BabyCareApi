package com.errahouti.BabyCareApi.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(){
        super();
    }

    public AlreadyExistsException(String msg){
        super(msg);
    }
}
