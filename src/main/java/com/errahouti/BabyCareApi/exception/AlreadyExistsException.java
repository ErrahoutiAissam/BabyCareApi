package com.errahouti.BabyCareApi.exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(){
        super();
    }

    public AlreadyExistsException(String msg){
        super(msg);
    }
}
