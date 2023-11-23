package com.errahouti.BabyCareApi.exception;

public class NotFoundException extends RuntimeException{

    public  NotFoundException(){
        super();
    }
    public NotFoundException(String thisEmailAlreadyExists){
        super(thisEmailAlreadyExists);
    };

}
