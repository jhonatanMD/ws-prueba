package com.ws.config.error;

public class DefaultException extends RuntimeException{

    public DefaultException(String error){
        super(error);
    }
}
