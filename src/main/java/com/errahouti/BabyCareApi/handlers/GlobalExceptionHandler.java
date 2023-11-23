package com.errahouti.BabyCareApi.handlers;

import com.errahouti.BabyCareApi.exception.AlreadyExistsException;
import com.errahouti.BabyCareApi.exception.EmailAlreadyExistsException;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.exception.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<?> alreadyExistsException(AlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistsException(EmailAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }
    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<?> tokenExpiredException(TokenExpiredException exception){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
    }




}
