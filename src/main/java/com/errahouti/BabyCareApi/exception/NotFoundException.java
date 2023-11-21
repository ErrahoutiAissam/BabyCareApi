package com.errahouti.BabyCareApi.exception;

public class NotFoundException extends Exception{

    public  NotFoundException(){
        super();
    }
    public NotFoundException(String thisEmailAlreadyExists){
        super(thisEmailAlreadyExists);
    };

}
