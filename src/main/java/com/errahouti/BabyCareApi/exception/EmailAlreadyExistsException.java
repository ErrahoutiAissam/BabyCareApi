package com.errahouti.BabyCareApi.exception;

public class EmailAlreadyExistsException extends AlreadyExistsException{
    public EmailAlreadyExistsException(){
        super();
    }
    public EmailAlreadyExistsException(String msg){
        super(msg);
    }

}
