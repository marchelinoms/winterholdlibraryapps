package com.indocyber.ExceptionHandling;

public class ObjectNotFound extends RuntimeException{

    public ObjectNotFound(String message){
        super(message);
    }
}
