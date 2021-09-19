package com.dov.firstapi.exceptionhandler;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(){
        super("Resource not found");
    }
}
